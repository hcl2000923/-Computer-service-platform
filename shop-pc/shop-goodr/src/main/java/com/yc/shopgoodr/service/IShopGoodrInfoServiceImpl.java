package com.yc.shopgoodr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class IShopGoodrInfoServiceImpl implements IShopGoodrInfoService {


    @Resource
    private IShopGoodrInfoMapper iShopGoodrInfoMapper;

    @Override
    public PageInfo<GoodInfo> findByPage(GoodInfo goodInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());// startPage()方法后面紧跟的是要分页的查询语句
        List<GoodInfo> list = iShopGoodrInfoMapper.findByTrem(goodInfo);
        PageInfo<GoodInfo> p = new PageInfo<>(list);
        return p;
    }

    /**
     * 展示查询前六条的数据
     * @return
     */
    @Override
    public List<GoodDetail> finds() {
        return iShopGoodrInfoMapper.findShowSix();
    }

    @Override
    public List<GoodInfo> findGoodInfoByTno(Integer tno) {
        GoodInfo goodInfo = new GoodInfo();
        GoodType goodType = new GoodType();
        goodType.setTno(tno);
        goodInfo.setGoodType(goodType);
        return iShopGoodrInfoMapper.findsByTno(goodInfo);
    }

}
