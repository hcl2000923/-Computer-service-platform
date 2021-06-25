package com.yc.shopgood.dao;


import com.yc.bean.GoodType;

public interface IShopGoodTypeMapper {

    /**
     * 服务或商品类型的添加
     *
     * @param goodType
     * @return
     */
    Integer addGoodType(GoodType goodType);


}
