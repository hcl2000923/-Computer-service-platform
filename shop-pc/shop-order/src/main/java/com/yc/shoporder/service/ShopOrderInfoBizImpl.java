package com.yc.shoporder.service;

import com.yc.bean.CartInfo;
import com.yc.bean.MemberInfo;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.exception.BizException;
import com.yc.shoporder.controller.CartInfoAction;
import com.yc.shoporder.controller.MemberInfoAction;
import com.yc.shoporder.dao.ShopOrderInfoMapper;
import com.yc.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-09 16:31
 */

@Service
@Transactional(rollbackFor = {Exception.class, BizException.class})
public class ShopOrderInfoBizImpl implements IShopOrderInfoBiz {
    @Resource
    private ShopOrderInfoMapper shopOrderInfoMapper;
    @Resource
    IShopOrderItemInfoBiz iShopOrderItemInfoBiz;

    @Resource
    private CartInfoAction cartInfoAction;
    //    @Resource
    //    private GoodAction goodAction;

    @Resource
    private MemberInfoAction memberInfoAction;

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {
        return 0;
    }

    @Override
    public int update(OrderInfo orderInfo) throws BizException {
        int t = shopOrderInfoMapper.update(orderInfo);
        if (t != 1) {
            throw new BizException("取消订单失败！");
        }
        return t;
    }

    @Override
    public List<OrderInfo> findOneToMany(OrderInfo orderInfo) {
        return shopOrderInfoMapper.findOneToMany(orderInfo);
    }

    @Override
    public List<OrderInfo> findByMno(Integer mno) {
        return shopOrderInfoMapper.findByMno(mno);
    }

    @Override
    public boolean genOrder(OrderInfo orderInfo, List<CartInfo> cartInfos, String descr, MemberInfo loginUser, HttpSession session) throws BizException {
        List<OrderItemInfo> items = new ArrayList<>();
        Integer[] cnos = new Integer[cartInfos.size()];
        //1.删除购物车中
        for (int i = 0; i < cartInfos.size(); i++) {
            CartInfo item = cartInfos.get(i);
            OrderItemInfo orderItemInfo = new OrderItemInfo();
            orderItemInfo.setMemberInfo(loginUser);
            orderItemInfo.setOrderInfo(orderInfo);
            orderItemInfo.setGoodDetail(item.getGoodDetail());
            orderItemInfo.setMoney(item.getGoodDetail().getPrice().multiply(new BigDecimal(item.getNum())));
            orderItemInfo.setNum(item.getNum());
            orderItemInfo.setDescr(descr);
            items.add(orderItemInfo);
//            //4.库存Balance减num
//            goodAction.deleteBalance(item.getGoodDetail().getSizeno(), item.getNum());
//            //5.销量加num=+num
//            goodAction.addSellingNum(item.getGoodDetail().getGoodInfo().getGno(), item.getNum());
            cnos[i] = item.getCno();
        }
        int flag1 = shopOrderInfoMapper.addOrderInfo(orderInfo);
        //1.删除购物车中
        Result res = cartInfoAction.deleteCart(cnos, loginUser);
        //2.订单详情表中插入多条
        int flag2 = iShopOrderItemInfoBiz.addOrderItemInfo(items);
        if (flag1 > 0 && res.getCode() == 1 && flag2 > 0) {
            session.removeAttribute("cartInfos");
            List<CartInfo> list = (List<CartInfo>) res.getData();
            loginUser.setCartInfoList(list);
            return true;
        } else {
            throw new BizException("下定出现异常！！！");
        }
    }
}
