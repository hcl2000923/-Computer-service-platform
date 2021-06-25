package com.yc.shopgoodr.controller;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgoodr.service.IShopGoodrDetailService;
import com.yc.shopgoodr.service.IShopGoodrInfoService;
import com.yc.shopgoodr.service.IShopGoodrTypeService;
import com.yc.vo.GoodInfoVO;
import com.yc.vo.Page;
import com.yc.vo.Result;
import com.yc.vo.Signal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodrController {
    @Resource
    private IShopGoodrDetailService iShopGoodrDetailService;

    @Resource
    private IShopGoodrTypeService iShopGoodrTypeService;

    @Resource
    private IShopGoodrInfoService iShopGoodrInfoService;

    @PostMapping("findGoodType")
    public Result findGoodType(GoodType goodType) {
        goodType.setTname("%" + goodType.getTname() + "%");
        Integer[] tnos = iShopGoodrTypeService.findTypeByLike(goodType);
        return Result.success("ok", tnos);
    }

    @PostMapping("findsSixInfo")
    public Result findsSixInfo() {
        List<GoodDetail> showSix = iShopGoodrInfoService.finds();
        return Result.success("ok", showSix);
    }

    @PostMapping("findAllType")
    public Result findAllType() {
        List<GoodType> allType = iShopGoodrTypeService.findAllType();
        return Result.success("success", allType);
    }

    @PostMapping("findAllByTnos")
    public Result findAllByTnos(Integer[] tnos) {
        List<GoodType> list = iShopGoodrTypeService.findAllByTnos(tnos);
        return Result.success("success", list);
    }

    @PostMapping("getInfo")
    public Result getInfo(GoodInfo goodInfo, Integer[] tnos, Page page, Signal signal) {
        PageInfo<GoodInfo> pageInfo = iShopGoodrInfoService.findByMultiAndPage(goodInfo, tnos, page, signal);
        return Result.success("success", pageInfo);
    }

    @PostMapping("findGoodInfoByGno")
    public Result findGoodInfoByGno(GoodInfo goodInfo) {
        GoodInfoVO goodInfoVO = iShopGoodrInfoService.findOneByGno(goodInfo);
        return Result.success("已找到该商品！", goodInfoVO);
    }

    @PostMapping("checkNum")
    public Result check(Integer[] nums, Integer[] sizenos) {
        return iShopGoodrDetailService.check(nums, sizenos);
    }
}
