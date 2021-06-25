package com.yc.shopindex.controller;

import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.vo.Result;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-22 19:06
 */
@Component
public class GoodrActionImpl implements GoodrAction {
    @Override
    public Result findAllType() {
        List<GoodType> goodTypeList = new ArrayList<>();
        GoodType goodType = new GoodType();
        goodType.setPic("images/img/001.jpg");
        goodType.setStatus(1);
        goodType.setTname("服务器加载失败！");
        goodType.setTno(0);
        goodTypeList.add(goodType);
        return Result.success("服务器断开连接！", goodTypeList);
    }

    @Override
    public Result findsSixInfo() {
        List<GoodDetail> goodDetailList = new ArrayList<>();
        for (int i = 0; i > -6; i--) {
            GoodDetail goodDetail = new GoodDetail();
            GoodInfo goodInfo = new GoodInfo();
            GoodType goodType = new GoodType();

            goodType.setTno(0);
            goodInfo.setGoodType(goodType);
            goodInfo.setGno(i);
            goodInfo.setGname("无法加载！");
            
            goodDetail.setGoodInfo(goodInfo);
            goodDetail.setSize("无商品！");
            goodDetail.setPrice(new BigDecimal(0));
            goodDetail.setSizeno(i);
            goodDetail.setShowPic("images/img/000.jpg");
            goodDetailList.add(goodDetail);
        }
        return Result.success("服务器断开连接！", goodDetailList);
    }
}
