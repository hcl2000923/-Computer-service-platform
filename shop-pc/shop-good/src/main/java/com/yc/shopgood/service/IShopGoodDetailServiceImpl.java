package com.yc.shopgood.service;

import com.yc.bean.GoodDetail;
import com.yc.shopgood.dao.IShopGoodDetailMapper;
import com.yc.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IShopGoodDetailServiceImpl implements IShopGoodDetailService {

    @Resource
    private IShopGoodDetailMapper iShopGoodDetailMapper;

    /**
     * 添加商品详情
     * @param goodDetail
     * @return
     */
    @Override
    public int add(GoodDetail goodDetail) {
        return iShopGoodDetailMapper.addGoodDetail(goodDetail);
    }

    /**
     * 更新商品详情
     * @param goodDetail
     * @return
     */
    @Override
    public int update(GoodDetail goodDetail) {
        return iShopGoodDetailMapper.update(goodDetail);
    }


}
