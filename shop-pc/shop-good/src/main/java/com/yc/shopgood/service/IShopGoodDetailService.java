package com.yc.shopgood.service;

import com.yc.bean.GoodDetail;

public interface IShopGoodDetailService {

    /**
     * 增加商品详情
     * @param goodDetail
     * @return
     */
    public int add(GoodDetail goodDetail);


    /**
     * 万能更新商品
     * @param goodDetail
     * @return
     */
    public int update(GoodDetail goodDetail);

//    /**
//     * 还不知道这个是干嘛的
//     * @param num
//     * @param sizeno
//     * @return
//     */
//    public String check(String num, String sizeno);
}
