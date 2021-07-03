package com.yc.shopindex.controller;

import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用menberinfo增加积分
 */
@FeignClient(value = "shop-memberinfo")
public interface MemberAction {

    @PostMapping("addPoint")
    Result addPoint(@RequestParam Integer mno, @RequestParam Integer moneyPoint);

}
