package com.yc.shopgood.dao;

import com.yc.bean.GoodDetail;
import com.yc.vo.Signal;


public interface IShopGoodDetailMapper {

    //增加商品详情
    Integer addGoodDetail(GoodDetail goodDetail);

    //商品详情的增加
    Integer update(GoodDetail goodDetail);

    //削减库存
    Integer updateBalance(GoodDetail goodDetail, Signal signal);
}
