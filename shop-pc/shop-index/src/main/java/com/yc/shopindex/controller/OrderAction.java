package com.yc.shopindex.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-order")
public interface OrderAction {

    @PostMapping("checkExist")
    public Result checkExist(@RequestParam String ono, @RequestParam Integer mno);

    @PostMapping("confirmOrder")
    public Result confirmOrder(@RequestParam String ono);

}
