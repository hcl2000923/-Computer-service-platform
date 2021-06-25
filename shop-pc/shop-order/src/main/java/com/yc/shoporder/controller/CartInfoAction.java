package com.yc.shoporder.controller;

import com.yc.bean.MemberInfo;
import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-cart")
public interface CartInfoAction {
    @PostMapping("deleteCart")
    Result deleteCart(@RequestParam Integer[] cnos, @RequestBody MemberInfo loginUser);
}
