package com.yc.shoporder.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "shop-cart")
public interface CartInfoAction {
    @PostMapping("deleteAll")
    public Result deleteAll(Integer[] cnos);
}
