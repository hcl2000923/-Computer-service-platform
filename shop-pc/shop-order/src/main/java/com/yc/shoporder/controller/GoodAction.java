package com.yc.shoporder.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "shop-good")
public interface GoodAction {
    @PostMapping("deleteBalance")
    public Result deleteBalance(Integer sizeno, Integer balance);

    @PostMapping("addSellingNum")
    public Result addSellingNum(Integer gno, Integer sellNum);
}
