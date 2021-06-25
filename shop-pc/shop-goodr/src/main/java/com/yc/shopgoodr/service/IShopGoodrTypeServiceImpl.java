package com.yc.shopgoodr.service;

import com.yc.bean.GoodType;
import com.yc.shopgoodr.dao.IShopGoodrTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IShopGoodrTypeServiceImpl implements IShopGoodrTypeService {

    @Resource
    private IShopGoodrTypeMapper iShopGoodrTypeMapper;

    @Override
    public List<GoodType> findAllByTnos(Integer[] tnos) {
        return iShopGoodrTypeMapper.findAllByTnos(tnos);
    }

    @Override
    public Integer[] findTypeByLike(GoodType goodType) {
        return iShopGoodrTypeMapper.findByLikeTrem(goodType);
    }

    @Override
    public List<GoodType> findAllType() {
        return iShopGoodrTypeMapper.findAllType();
    }
}
