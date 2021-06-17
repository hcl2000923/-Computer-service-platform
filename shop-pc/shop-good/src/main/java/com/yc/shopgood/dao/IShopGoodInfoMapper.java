package com.yc.shopgood.dao;


import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;

import java.util.List;

public interface IShopGoodInfoMapper {

    /**
     * 商品添加
     * @param goodInfo
     * @return
     */
    public int addGoodInfo(GoodInfo goodInfo);


    /**
     * 商品信息修改
     * @param goodInfo
     * @return
     */
    public int update(GoodInfo goodInfo);



}
