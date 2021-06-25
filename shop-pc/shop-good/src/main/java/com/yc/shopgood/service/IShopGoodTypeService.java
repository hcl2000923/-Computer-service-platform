package com.yc.shopgood.service;

import com.yc.bean.GoodType;

public interface IShopGoodTypeService {

    /**
     * 修改商品或服务类别
     *
     * @param goodType
     * @return
     */
    Integer add(GoodType goodType);
}
