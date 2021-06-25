package com.yc.shoporder.controller;

import com.yc.bean.GoodDetail;
import com.yc.vo.Result;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "shop-good")
public interface GoodAction {
    @PostMapping("addSellNumAndDownBalance")
    Result addSellNumAndDownBalance(@Param(value = "goodDetails") List<GoodDetail> goodDetails);

    @PostMapping("downSellNumAndAddBalance")
    Result downSellNumAndAddBalance(@Param(value = "goodDetails") List<GoodDetail> goodDetails);
}
