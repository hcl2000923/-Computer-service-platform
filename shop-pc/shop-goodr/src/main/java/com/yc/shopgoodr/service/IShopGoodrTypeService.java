package com.yc.shopgoodr.service;

import com.yc.bean.GoodType;

import java.util.List;

public interface IShopGoodrTypeService {
    
    public List<GoodType> findAllByTnos(Integer[] tnos);

    public Integer[] findTypeByLike(GoodType goodType);

    public List<GoodType> findAllType();

}
