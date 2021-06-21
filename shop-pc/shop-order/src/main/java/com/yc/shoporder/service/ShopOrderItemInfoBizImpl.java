package com.yc.shoporder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.enums.OrderInfoBuyWayEnum;
import com.yc.enums.OrderInfoPayStatusEnum;
import com.yc.exception.BizException;
import com.yc.shoporder.dao.ShopOrderItemInfoMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-09 16:31
 */
@Service
@Transactional
public class ShopOrderItemInfoBizImpl implements IShopOrderItemInfoBiz {
    @Resource
    ShopOrderItemInfoMapper shopOrderItemInfoMapper;

    @Override
    public int addOrderItemInfo(List<OrderItemInfo> orderItemInfoList) {
        return shopOrderItemInfoMapper.addOrderItemInfo(orderItemInfoList);
    }

    @Override
    public int update(OrderItemInfo orderItemInfo) {
        return 0;
    }

    @Override
    public int delete(List<OrderItemInfo> orderItemInfoList) {
        return 0;
    }

    @Override
    public List<OrderItemInfo> findByMulti(OrderItemInfo orderItemInfo) {
        return shopOrderItemInfoMapper.findByMulti(orderItemInfo);
    }

    @Override
    public List<OrderItemInfo> checkStatus(OrderItemInfo orderItemInfo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(OrderInfoPayStatusEnum.NOPAY.getCode());
        orderInfo.setBuyWay(OrderInfoBuyWayEnum.ONLINEPAY.getMessage());
        orderItemInfo.setOrderInfo(orderInfo);
        return shopOrderItemInfoMapper.findByMulti(orderItemInfo);
    }

    @Override
    public PageInfo<OrderItemInfo> findByMultiAndPage(OrderItemInfo orderItemInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<OrderItemInfo> list = shopOrderItemInfoMapper.findByMulti(orderItemInfo);
        PageInfo<OrderItemInfo> p = new PageInfo<>(list);
        return p;
    }

    @Override
    public Integer findMnoByOno(OrderInfo o) throws BizException {
        Integer mno = shopOrderItemInfoMapper.findMnoByOno(o.getOno());
        if (mno == null) {
            throw new BizException("订单号未能找到与之对应的用户id");
        }
        return mno;
    }
}
