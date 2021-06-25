package com.yc.shopgood.service;

import com.yc.bean.GoodInfo;

public interface IShopGoodInfoService {

    /**
     * 添加商品
     *
     * @param goodInfo
     * @return
     */
    Integer add(GoodInfo goodInfo);


    /**
     * 修改评分
     *
     * @param goodInfo
     * @return
     */
    Integer updatePoint(GoodInfo goodInfo);

    //修改销售量
    Integer addSellingNum(GoodInfo goodInfo);

    Integer downSellingNum(GoodInfo goodInfo);
}
