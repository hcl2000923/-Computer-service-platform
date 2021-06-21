package com.yc.shopgoodr.control;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.shopgoodr.service.IShopGoodrDetailService;
import com.yc.shopgoodr.service.IShopGoodrInfoService;
import com.yc.shopgoodr.service.IShopGoodrTypeService;
import com.yc.vo.Page;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodrAction {
    @Resource
    private IShopGoodrDetailService iShopGoodrDetailService;

    @Resource
    private IShopGoodrTypeService iShopGoodrTypeService;

    @Resource
    private IShopGoodrInfoService iShopGoodrInfoService;

    @PostMapping("findGoodType")
    private Result findGoodType(GoodType goodType){
        List<GoodType> goodTypes = iShopGoodrTypeService.findType(goodType);
        return Result.success("ok",goodTypes);
    }

//    @PostMapping("findGoodType")
//    private Result findGoodTypePage(GoodType goodType){
//        List<GoodType> type = iShopGoodrTypeService.findType(goodType);
//        return Result.success("ok",type);
//    }

//    @PostMapping("findInfo")
//    private Result findInfo(GoodInfo goodInfo){
//        List<GoodInfo> goodInfos = iShopGoodrInfoMapper.findByTrem(goodInfo);
//        return Result.success("ok",goodInfo);
//    }

    @PostMapping("findsSixInfo")
    private Result findsSixInfo(){
        List<GoodDetail> showSix = iShopGoodrInfoService.finds();
        return Result.success("ok",showSix);
    }

    @PostMapping("check")
    private Result check( String[] nums,String[] sizenos){
        String msg = iShopGoodrDetailService.check(nums, sizenos);
        return Result.success("success",msg);
    }



    @PostMapping("findDiv")
    private Result findMenu(){
        List<GoodType> type = iShopGoodrTypeService.findType();
        return Result.success("success",type);
    }

    @RequestMapping("getInfo")
    private Result getInfo(Integer[] tnos) {
        List<GoodInfo> goodInfoByTno = new ArrayList<>();
        for (Integer index : tnos) {
            goodInfoByTno.addAll(iShopGoodrInfoService.findGoodInfoByTno(index));
        }
        return Result.success("success", goodInfoByTno);
    }
    @RequestMapping("getDetail")
    private Result getDetail(GoodDetail goodDetail) {
        List<GoodDetail> goodDetail1 = iShopGoodrDetailService.findDetailAll(goodDetail);
        return Result.success("success", goodDetail1);
    }

}
