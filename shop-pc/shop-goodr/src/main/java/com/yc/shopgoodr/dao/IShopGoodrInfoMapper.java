package com.yc.shopgoodr.dao;


import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.vo.Signal;

import java.util.List;

public interface IShopGoodrInfoMapper {
    //查找商品
    List<GoodInfo> findByTrem(GoodInfo goodInfo, Integer[] tnos, Signal signal);

    //每一个类型的前六条，查展示页面的前6条
    List<GoodDetail> findShowSix();
}
