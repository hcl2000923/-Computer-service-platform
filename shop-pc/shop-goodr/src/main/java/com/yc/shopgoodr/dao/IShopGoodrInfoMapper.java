package com.yc.shopgoodr.dao;


import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;

import java.util.List;

public interface IShopGoodrInfoMapper {
    //查找商品
    public List<GoodInfo> findByTrem(GoodInfo t);


    //查展示页面的前6条
    //每一个类型的前六条
    public List<GoodDetail> findShowSix();



    //评论总数
    public int getCommentsTotal(GoodDetail goodDetail);
}
