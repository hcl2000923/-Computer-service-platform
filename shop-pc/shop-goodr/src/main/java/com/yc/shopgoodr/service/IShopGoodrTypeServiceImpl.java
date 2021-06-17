package com.yc.shopgoodr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.bean.adminInfo;
import com.yc.shopgoodr.dao.IShopGoodrTypeMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IShopGoodrTypeServiceImpl implements IShopGoodrTypeService {

    @Resource
    private IShopGoodrTypeMapper iShopGoodrTypeMapper;

    @Override
    public PageInfo<GoodType> findBytrem(GoodType goodType, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());// startPage()方法后面紧跟的是要分页的查询语句
        List<GoodType> list = iShopGoodrTypeMapper.findByTrem(goodType);
        PageInfo<GoodType> p = new PageInfo<>(list);
        return p;
    }

    @Override
    public List<GoodType> findType(GoodType goodType) {
        return iShopGoodrTypeMapper.findByTrem(goodType);
    }
}
