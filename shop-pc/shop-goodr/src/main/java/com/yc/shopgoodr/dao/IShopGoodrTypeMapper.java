package com.yc.shopgoodr.dao;


import com.yc.bean.GoodType;

import java.util.List;

public interface IShopGoodrTypeMapper {


    //服务名称类型查询
    public List<GoodType> findByTrem(GoodType goodType);


}
