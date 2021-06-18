package com.yc.shopcomment.controller;

import com.yc.bean.GoodDetail;
import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "shop-goodr")
public interface GoodDetailAction {

    @GetMapping("findGnoBySizeno")
    public Result findGnoBySizeno(GoodDetail goodDetail);

}
