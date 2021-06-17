package com.yc.shopgood.service;

import com.yc.bean.GoodType;
import com.yc.shopgood.dao.IShopGoodTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IShopGoodTypeServiceImpl implements IShopGoodTypeMapper {

    @Resource
    private IShopGoodTypeMapper iShopGoodTypeMapper;
    @Override
    public int addGoodType(GoodType goodType) {
        return iShopGoodTypeMapper.addGoodType(goodType);
    }
}
