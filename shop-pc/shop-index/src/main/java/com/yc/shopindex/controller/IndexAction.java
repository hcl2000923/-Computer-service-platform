package com.yc.shopindex.controller;

import com.yc.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: shop-book
 * @description:
 * @author: 作者
 * @create: 2021-05-22 16:09
 */
@RestController
public class IndexAction {
    @Resource
    private GoodrAction goodrAction;

    @PostMapping("findAllType")
    public Result findAllType() {
        return goodrAction.findAllType();
    }

    @PostMapping("findsSixInfo")
    public Result findsSixInfo() {
        return goodrAction.findsSixInfo();
    }

}
