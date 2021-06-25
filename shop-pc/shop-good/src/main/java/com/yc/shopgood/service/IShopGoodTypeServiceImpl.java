package com.yc.shopgood.service;

import com.yc.bean.GoodType;
import com.yc.shopgood.dao.IShopGoodTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class IShopGoodTypeServiceImpl implements IShopGoodTypeService {

    @Resource
    private IShopGoodTypeMapper iShopGoodTypeMapper;


    @Override
    public Integer add(GoodType goodType) {
        return null;
    }
}
