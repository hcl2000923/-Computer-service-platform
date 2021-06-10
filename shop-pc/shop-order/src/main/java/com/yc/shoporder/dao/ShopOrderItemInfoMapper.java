package com.yc.shoporder.dao;

import com.yc.bean.OrderItemInfo;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 14:09
 */

public interface ShopOrderItemInfoMapper {

    //新增orderinfoItemInfo
    int addOrderItemInfo(List<OrderItemInfo> orderItemInfoList);

    //更新操作
    int update(OrderItemInfo orderItemInfo);

    //删除操作
    int delete(List<OrderItemInfo> orderItemInfoList);

    //分页or不分页
    List<OrderItemInfo> findByMulti(OrderItemInfo orderItemInfo);

}
