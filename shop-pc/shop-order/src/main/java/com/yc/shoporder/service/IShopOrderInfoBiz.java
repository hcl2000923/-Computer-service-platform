package com.yc.shoporder.service;

import com.yc.bean.OrderInfo;
import com.yc.exception.BizException;

import java.util.List;

public interface IShopOrderInfoBiz {
    /**
     * 新增orderinfo
     *
     * @param orderInfo
     * @return
     */
    int addOrderInfo(OrderInfo orderInfo);

    /**
     * 更新操作
     *
     * @param orderInfo
     * @return
     */
    int update(OrderInfo orderInfo) throws BizException;

    /**
     * 分页连表查询  总条数
     *
     * @param orderInfo
     * @return
     */
    List<OrderInfo> findOneToMany(OrderInfo orderInfo);

    /**
     * 通过mno查询支付订单
     *
     * @param mno
     * @return
     */
    List<OrderInfo> findByMno(Integer mno);
}
