package com.yc.shopgood.controller;

import com.google.gson.Gson;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.shopgood.service.IShopGoodDetailService;
import com.yc.shopgood.service.IShopGoodInfoService;
import com.yc.shopgood.service.IShopGoodTypeService;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodController {
    @Resource
    private IShopGoodInfoService iShopGoodInfoService;

    @Resource
    private IShopGoodDetailService iShopGoodDetailService;

    @Resource
    private IShopGoodTypeService iShopGoodTypeService;

    @PostMapping("addSellNumAndDownBalance")
    public Result addSellNumAndDownBalance(@RequestBody List<GoodDetail> goodDetails) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(goodDetails));
        Result result = iShopGoodDetailService.addSellNumAndDownBalance(goodDetails);
        return result;
    }

    @PostMapping("downSellNumAndAddBalance")
    public Result downSellNumAndAddBalance(@RequestBody List<GoodDetail> goodDetails) {
        Result result = iShopGoodDetailService.downSellNumAndAddBalance(goodDetails);
        return result;
    }

    @PostMapping("downBalance")
    public Result downBalance(GoodDetail goodDetail) {
        Integer integer = iShopGoodDetailService.downBalance(goodDetail);
        return Result.success("hao", integer);
    }

    /**
     * 修改评分
     *
     * @param goodInfo
     * @return
     */
    @PostMapping("doUpdatePoint")
    @ResponseBody
    public Result doUpdatePoint(GoodInfo goodInfo) {
        int i = iShopGoodInfoService.updatePoint(goodInfo);
        if (i != 0) {
            return Result.success("修改成功", null);
        }
        return Result.success("修改失败", null);
    }

}
