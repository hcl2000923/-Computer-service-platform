package com.yc.shopfile.controller;

import com.yc.bean.MemberInfo;
import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: shop-book
 * @description:Feign接口对应一个服务
 * @author: 作者
 * @create: 2021-05-22 16:03
 */

@FeignClient(value = "shop-memberinfo")
public interface IMemberInfoAction {
    @GetMapping("updatePhoto")
    public Result updatePhoto(MemberInfo memberInfo);
}
