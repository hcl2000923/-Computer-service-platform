package com.yc.shopindex.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用
 */
@FeignClient(value = "shop-goodr", fallback = GoodrActionImpl.class)
public interface GoodrAction {

    @PostMapping("findAllType")
    Result findAllType();

    @PostMapping("findsSixInfo")
    Result findsSixInfo();

}
