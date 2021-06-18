package com.yc.shopgoodr.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.vo.Page;

import java.util.List;

public interface IShopGoodrTypeService {


    /**
     * 查商品类型。通过类型查
     * @param
     * @return
     */
    public PageInfo<GoodType> findBytrem(GoodType goodType, Page page);


    public List<GoodType> findType(GoodType goodType);

}
