package com.yc.shoporder.service;

import com.yc.bean.*;
import com.yc.enums.OrderInfoBuyWayEnum;
import com.yc.exception.BizException;
import com.yc.shoporder.controller.CartInfoAction;
import com.yc.shoporder.controller.GoodAction;
import com.yc.shoporder.dao.ShopOrderInfoMapper;
import com.yc.util.MailUtils;
import com.yc.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

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
    ExecutorService executorService;

    @Resource
    private ShopOrderInfoMapper shopOrderInfoMapper;
    @Resource
    IShopOrderItemInfoBiz iShopOrderItemInfoBiz;

    @Resource
    private CartInfoAction cartInfoAction;
    @Resource
    private GoodAction goodAction;

    @Value("${user}")
    private String user;

    @Override
    public int update(OrderInfo orderInfo) throws BizException {
        int t = shopOrderInfoMapper.update(orderInfo);
        if (t != 1) {
            throw new BizException("取消订单失败！");
        }
        return t;
    }

    @Override
    public Integer confirmOrder(OrderInfo orderInfo) throws BizException {
        int t = shopOrderInfoMapper.update(orderInfo);
        if (t != 1) {
            throw new BizException("下单失败！");
        } else {
            executorService.execute(() -> {
                MailUtils.sendMail(user, "订单号：" + orderInfo.getOno(), "新订单请及时查收");
            });
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
        List<GoodDetail> goodDetails = new ArrayList<>();
        GoodDetail goodDetail = null;
        GoodInfo goodInfo = null;
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
            //4.库存Balance减num
            goodDetail = new GoodDetail();
            goodDetail.setSizeno(item.getGoodDetail().getSizeno());
            goodDetail.setBalance(item.getNum());

            goodInfo = new GoodInfo();
            goodInfo.setGno(item.getGoodDetail().getGoodInfo().getGno());
            goodInfo.setSellNum(item.getNum());
            goodDetail.setGoodInfo(goodInfo);

            goodDetails.add(goodDetail);
            //5.销量加num=+num

            cnos[i] = item.getCno();
        }
        int flag1 = shopOrderInfoMapper.addOrderInfo(orderInfo);
        //1.订单详情表中插入多条
        int flag2 = iShopOrderItemInfoBiz.addOrderItemInfo(items);
        //2.删除购物车中
        Result res = cartInfoAction.deleteCart(cnos, loginUser);
        if (res.getCode() != 1) {
            throw new BizException("下定出现异常！！！");
        }
        //3.销量加sellNum-=num
        //4.库存Balance+=num
        Result result = goodAction.addSellNumAndDownBalance(goodDetails);
        if (result.getCode() != 1) {
            throw new BizException("下定出现异常！！！");
        }
        if (flag1 > 0 && flag2 > 0) {
            session.removeAttribute("cartInfos");
            List<CartInfo> list = (List<CartInfo>) res.getData();
            loginUser.setCartInfoList(list);
            return true;
        } else {
            throw new BizException("下定出现异常！！！");
        }
    }

    @Override
    public Integer buyByCash(OrderInfo orderInfo) {
        orderInfo.setBuyWay(OrderInfoBuyWayEnum.CASH.getMessage());
        int flag = shopOrderInfoMapper.update(orderInfo);
        if (flag == 1) {
            executorService.execute(() -> {
                MailUtils.sendMail(user, "订单号：" + orderInfo.getOno(), "新订单请及时查收");
            });
        }
        return flag;
    }

    @Override
    public List<OrderInfo> find(OrderInfo orderInfo, Integer mno) {
        return shopOrderInfoMapper.find(orderInfo, mno);
    }

    @Override
    public Boolean downSellNumAndAddBalance(OrderInfo orderInfo) throws BizException {
        //1.遍历查询订单里的商品及数量
        List<OrderInfo> oneToMany = shopOrderInfoMapper.findOneToMany(orderInfo);
        List<GoodDetail> goodDetails = new ArrayList<>();
        OrderInfo item = oneToMany.get(0);
        item.getOrderItemInfoList().forEach((it) -> {
            GoodDetail goodDetail = it.getGoodDetail();
            goodDetail.setBalance(it.getNum());
            goodDetail.setGoodInfo(it.getGoodDetail().getGoodInfo());
            goodDetails.add(goodDetail);
        });
        //2.销量加sellNum-=num
        //3.库存Balance+=num
        Result result = goodAction.downSellNumAndAddBalance(goodDetails);

        if (result.getCode() == 1) {
            return true;
        } else {
            throw new BizException("下定出现异常！！！");
        }
    }
}
