package com.yc.shopgoodr.control;

import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.shopgoodr.service.IShopGoodrDetailService;
import com.yc.shopgoodr.service.IShopGoodrTypeService;
import com.yc.vo.Page;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private IShopGoodrInfoMapper iShopGoodrInfoMapper;

    @PostMapping("findGoodType")
    private Result findGoodType(GoodType goodType){
        List<GoodType> goodTypes = iShopGoodrTypeService.findType(goodType);
        return Result.success("ok",goodTypes);
    }

    @PostMapping("findGoodTypePage")
    private Result findGoodTypePage(GoodType goodType,Page page){
        PageInfo<GoodType> goodTypes = iShopGoodrTypeService.findBytrem(goodType,page);
        return Result.success("ok",goodTypes);
    }

    @PostMapping("findInfo")
    private Result findInfo(GoodInfo goodInfo){
        List<GoodInfo> goodInfos = iShopGoodrInfoMapper.findByTrem(goodInfo);
        return Result.success("ok",goodInfo);
    }

    @PostMapping("findsSixInfo")
    private Result findsSixInfo(){
        List<GoodDetail> showSix = iShopGoodrInfoMapper.findShowSix();
        return Result.success("ok",showSix);
    }

    @PostMapping("check")
    private Result check( String[] nums,String[] sizenos){
        String msg = iShopGoodrDetailService.check(nums, sizenos);
        return Result.success("success",msg);
    }








}
