package com.yc.shopgoodr.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.vo.GoodInfoVO;
import com.yc.vo.Page;
import com.yc.vo.Signal;

import java.util.List;

public interface IShopGoodrInfoService {
    /**
     * 查前六个
     *
     * @return
     */
    List<GoodDetail> finds();

    PageInfo<GoodInfo> findByMultiAndPage(GoodInfo goodInfo, Integer[] tnos, Page page, Signal signal);

    GoodInfoVO findOneByGno(GoodInfo goodInfo);
}
