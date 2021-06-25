package com.yc.shopgood.service;

import com.yc.bean.GoodDetail;
import com.yc.vo.Result;

import java.util.List;

public interface IShopGoodDetailService {

    /**
     * 增加商品详情
     *
     * @param goodDetail
     * @return
     */
    Integer add(GoodDetail goodDetail);


    /**
     * 万能更新商品
     *
     * @param goodDetail
     * @return
     */
    Integer update(GoodDetail goodDetail);

    Integer downBalance(GoodDetail goodDetail);

    Integer addBalance(GoodDetail goodDetail);

    Result addSellNumAndDownBalance(List<GoodDetail> goodDetails);

    Result downSellNumAndAddBalance(List<GoodDetail> goodDetails);
}
