package com.yc.shopgood.control;

import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.shopgood.dao.IShopGoodDetailMapper;
import com.yc.shopgood.dao.IShopGoodTypeMapper;
import com.yc.shopgood.service.IShopGoodInfoService;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class goodInfoAction {
    @Resource
    private IShopGoodInfoService iShopGoodInfoService;

    @Resource
    private IShopGoodDetailMapper iShopGoodDetailMapper;

    @Resource
    private IShopGoodTypeMapper iShopGoodTypeMapper;


    /**
     * 修改评分
     * @param goodInfo
     * @return
     */
    @PostMapping("doUpdatePoint")
    @ResponseBody
    private Result doUpdatePoint(GoodInfo goodInfo){
        int i = iShopGoodInfoService.updatePoint(goodInfo);
        if(i!=0){
            return Result.success("修改成功",null);
        }
        return Result.success("修改失败",null);
    }


    @PostMapping("doUpdateDetail")
    @ResponseBody
    private Result doUpdateDetail(GoodDetail goodDetail){
        int i = iShopGoodDetailMapper.addGoodDetail(goodDetail);
        if (i!=0){
            return Result.success("修改成功",null);
        }
        return Result.success("修改失败",null);
    }



}
