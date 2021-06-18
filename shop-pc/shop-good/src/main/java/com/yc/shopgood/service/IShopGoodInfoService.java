package com.yc.shopgood.service;

import com.yc.bean.GoodInfo;

public interface IShopGoodInfoService {

    /**
     * 添加商品
     * @param goodInfo
     * @return
     */
    public int add(GoodInfo goodInfo);


    /**
     * 修改评分
     * @param goodInfo
     * @return
     */
    public int updatePoint(GoodInfo goodInfo);
}
