package com.yc.shoporder.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.exception.BizException;
import com.yc.vo.Page;

import java.util.List;

public interface IShopOrderItemInfoBiz {
    //新增orderinfoItemInfo
    int addOrderItemInfo(List<OrderItemInfo> orderItemInfoList);

    //更新操作
    int update(OrderItemInfo orderItemInfo);

    //删除操作
    int delete(List<OrderItemInfo> orderItemInfoList);

    //不分页
    List<OrderItemInfo> findByMulti(OrderItemInfo orderItemInfo);

    List<OrderItemInfo> checkStatus(OrderItemInfo orderItemInfo);

    //分页
    PageInfo<OrderItemInfo> findByMultiAndPage(OrderItemInfo orderItemInfo, Page page);

    Integer findMnoByOno(OrderInfo o) throws BizException;
}
