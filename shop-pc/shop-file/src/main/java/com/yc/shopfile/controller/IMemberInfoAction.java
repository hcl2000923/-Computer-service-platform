package com.yc.shopfile.controller;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: shop-book
 * @description:Feign接口对应一个服务
 * @author: 作者
 * @create: 2021-05-22 16:03
 */
@FeignClient(value = "shop-memberinfo")
public interface IMemberInfoAction {
//    @GetMapping("queryByPage")
//    public Result queryByPage(CrShow crShow);
//
//    @GetMapping("queryByPage1")
//    public Result queryByPage1(CrShow1 crShow1);
}
