package com.yc.shoporder.service;

import com.yc.bean.OrderInfo;
import com.yc.exception.BizException;
import com.yc.shoporder.dao.ShopOrderInfoMapper;
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
@Transactional(rollbackFor = Exception.class)
public class ShopOrderInfoBizImpl implements IShopOrderInfoBiz {
    @Resource
    private ShopOrderInfoMapper shopOrderInfoMapper;

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
}
