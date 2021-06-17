package com.yc.shopgoodr.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.vo.Page;

import java.util.List;
import java.util.Map;

public interface IShopGoodrDetailService {

    /**
     * 查找详情
     * @param goodDetail
     * @return
     */
    public List<GoodDetail> findBytrem(GoodDetail goodDetail);


    /**
     * 分页
     * @param goodDetail
     * @param page
     * @return
     */
    public PageInfo<GoodDetail> findByPage(GoodDetail goodDetail, Page page);

    /**
     * 验证库存剩余
     * @param nums
     * @param sizenos
     * @return
     */
    public String check(String[] nums, String[] sizenos);

}
