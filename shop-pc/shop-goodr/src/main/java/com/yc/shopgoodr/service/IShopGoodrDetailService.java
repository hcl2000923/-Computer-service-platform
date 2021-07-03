package com.yc.shopgoodr.service;

import com.yc.bean.GoodDetail;
import com.yc.exception.BizException;
import com.yc.vo.Result;

public interface IShopGoodrDetailService {

    Result check(Integer[] nums, Integer[] sizenos);

    Result findBySizeno(GoodDetail goodDetail) throws BizException;
}
