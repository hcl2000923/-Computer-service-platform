package com.yc.shopgoodr.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.vo.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IShopGoodrInfoService {



    /**
     * 查所有加分页
     * @param goodInfo
     * @param page
     * @return
     */
    public PageInfo<GoodInfo> findByPage(GoodInfo goodInfo, Page page);

    /**
     * 查前六个
     * @return
     */
    public List<GoodDetail> finds();
}
