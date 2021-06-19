package com.yc.shoporder.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(value = "shop-memberinfo")
public interface MemberInfoAction {
    @PostMapping("addPoint")
    public Result addPoint(BigDecimal totalPrice);
}
