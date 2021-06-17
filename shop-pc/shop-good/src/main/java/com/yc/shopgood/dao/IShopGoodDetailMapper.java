package com.yc.shopgood.dao;


import com.yc.bean.GoodDetail;


public interface IShopGoodDetailMapper {

    //增加商品详情
    public int addGoodDetail(GoodDetail goodDetail);

    //商品详情的增加
    public int update(GoodDetail goodDetail);




}
