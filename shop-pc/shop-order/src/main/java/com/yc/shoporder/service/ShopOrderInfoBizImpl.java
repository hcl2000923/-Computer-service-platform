package com.yc.shoporder.service;

import com.yc.bean.CartInfo;
import com.yc.bean.MemberInfo;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.exception.BizException;
import com.yc.shoporder.controller.CartInfoAction;
import com.yc.shoporder.controller.GoodDetailAction;
import com.yc.shoporder.controller.GoodInfoAction;
import com.yc.shoporder.dao.ShopOrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Transactional(rollbackFor = Exception.class)
public class ShopOrderInfoBizImpl implements IShopOrderInfoBiz {
    @Resource
    private ShopOrderInfoMapper shopOrderInfoMapper;
    @Resource
    private CartInfoAction cartInfoAction;
    @Resource
    private GoodDetailAction goodDetailAction;
    @Resource
    private GoodInfoAction goodInfoAction;

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
    public boolean genOrder(OrderInfo orderInfo, List<CartInfo> cartInfos, String descr, MemberInfo loginUser) {
        List<OrderItemInfo> items = new ArrayList<>();
        cartInfos.forEach((item) -> {
            OrderItemInfo orderItemInfo = new OrderItemInfo();
            orderItemInfo.setMemberInfo(loginUser);
            orderItemInfo.setOrderInfo(orderInfo);
            orderItemInfo.setGoodDetail(item.getGoodDetail());
            orderItemInfo.setMoney(item.getGoodDetail().getPrice().multiply(new BigDecimal(item.getNum())));
            orderItemInfo.setNum(item.getNum());
            orderItemInfo.setDescr(descr);
            items.add(orderItemInfo);
        });
        shopOrderInfoMapper.addOrderInfo(orderInfo);
        //1.删除购物车中
        //2.订单详情表中插入多条
        //3.增加会员积分
        //4.库存减num
        //5.销量加num=+num
        return false;
    }
}
