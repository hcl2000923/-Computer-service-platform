package com.yc.shoporder.dao;

import com.yc.bean.OrderInfo;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 14:09
 */

public interface ShopOrderInfoMapper {

    //新增orderinfo
    int addOrderInfo(OrderInfo orderInfo);

    //更新操作
    int update(OrderInfo orderInfo);

    //分页连表查询  总条数
    List<OrderInfo> findOneToMany(OrderInfo orderInfo);

    //通过mno查询支付订单
    List<OrderInfo> findByMno(Integer mno);
    
}
