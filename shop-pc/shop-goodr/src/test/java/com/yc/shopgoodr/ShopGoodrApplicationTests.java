package com.yc.shopgoodr;

import com.yc.bean.GoodInfo;
import com.yc.shopgoodr.dao.IShopGoodrDetailsMapper;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.shopgoodr.dao.IShopGoodrTypeMapper;
import com.yc.shopgoodr.service.IShopGoodrDetailService;
import com.yc.shopgoodr.service.IShopGoodrInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ShopGoodrApplicationTests {

    @Resource
    private IShopGoodrTypeMapper iShopGoodrTypeMapper;

    @Resource
    private IShopGoodrInfoMapper iShopGoodrInfoMapper;

    @Resource
    private IShopGoodrDetailsMapper iShopGoodrDetailsMapper;


    @Resource
    private IShopGoodrDetailService iShopGoodrDetailService;


    @Resource
    private IShopGoodrInfoService iShopGoodrInfoService;

    @Test
    void contextLoads() {

    }

    @Test
    void findByTrem() {
        GoodInfo goodInfo = new GoodInfo();
        Integer[] tnos = new Integer[]{1, 13};
//        System.out.println(iShopGoodrInfoMapper.findByTrem(goodInfo, tnos));

    }


}
