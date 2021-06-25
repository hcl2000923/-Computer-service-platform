package com.yc.shopgoodr.dao;


import com.yc.bean.GoodDetail;
import com.yc.vo.Signal;

import java.util.List;

public interface IShopGoodrDetailsMapper {


    List<GoodDetail> findAllDetail(GoodDetail goodDetail, Signal signal);

}
