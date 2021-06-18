package com.yc.shopcomment.controller;

import com.yc.bean.MemberInfo;
import com.yc.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "shop-file")
public interface IFileAction {

    @GetMapping("commentUpload")
    public Result commentUpload(MemberInfo memberInfo);

}
