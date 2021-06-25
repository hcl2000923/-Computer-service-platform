package com.yc.shopgood.service;

import com.yc.bean.GoodInfo;
import com.yc.shopgood.dao.IShopGoodInfoMapper;
import com.yc.vo.Signal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class IShopGoodInfoServiceImpl implements IShopGoodInfoService {

    @Resource
    private IShopGoodInfoMapper iShopGoodInfoMapper;

    /**
     * 添加服务
     *
     * @param goodInfo
     * @return
     */
    @Override
    public Integer add(GoodInfo goodInfo) {
        return iShopGoodInfoMapper.addGoodInfo(goodInfo);
    }

    /**
     * 更新评分
     *
     * @param goodInfo
     * @return
     */
    @Override
    public Integer updatePoint(GoodInfo goodInfo) {
        if (goodInfo.getPoint() != null) {
            return iShopGoodInfoMapper.update(goodInfo);
        }
        return 0;
    }

    @Override
    public Integer addSellingNum(GoodInfo goodInfo) {
        Signal signal = new Signal();
        signal.setSymbols("+");
        return iShopGoodInfoMapper.updateSellNum(goodInfo, signal);
    }

    @Override
    public Integer downSellingNum(GoodInfo goodInfo) {
        Signal signal = new Signal();
        signal.setSymbols("-");
        return iShopGoodInfoMapper.updateSellNum(goodInfo, signal);
    }
}
