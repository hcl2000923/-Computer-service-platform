package com.yc.shopgood.service;

import com.yc.bean.GoodInfo;
import com.yc.shopgood.dao.IShopGoodInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IShopGoodInfoServiceImpl implements IShopGoodInfoService {

    @Resource
    private IShopGoodInfoMapper iShopGoodInfoMapper;

    /**
     * 添加服务
     * @param goodInfo
     * @return
     */
    @Override
    public int add(GoodInfo goodInfo) {
        return iShopGoodInfoMapper.addGoodInfo(goodInfo);
    }

    /**
     * 更新评分
     * @param goodInfo
     * @return
     */
    @Override
    public int updatePoint(GoodInfo goodInfo) {
        if(goodInfo.getPoint()!=null) {
            return iShopGoodInfoMapper.update(goodInfo);
        }
        return 0;
    }
}
