package com.yc.shopgood.dao;


import com.yc.bean.GoodInfo;
import com.yc.vo.Signal;

public interface IShopGoodInfoMapper {

    /**
     * 商品添加
     *
     * @param goodInfo
     * @return
     */
    Integer addGoodInfo(GoodInfo goodInfo);


    /**
     * 商品信息修改
     *
     * @param goodInfo
     * @return
     */
    Integer update(GoodInfo goodInfo);

    Integer updateSellNum(GoodInfo goodInfo, Signal signal);

}
