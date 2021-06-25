package com.yc.shopgood.service;

import com.yc.bean.GoodDetail;
import com.yc.shopgood.dao.IShopGoodDetailMapper;
import com.yc.vo.Result;
import com.yc.vo.Signal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IShopGoodDetailServiceImpl implements IShopGoodDetailService {

    @Resource
    IShopGoodDetailMapper iShopGoodDetailMapper;

    @Resource
    IShopGoodInfoService iShopGoodInfoService;

    @Override
    public Integer add(GoodDetail goodDetail) {
        return null;
    }

    @Override
    public Integer update(GoodDetail goodDetail) {
        return null;
    }

    @Override
    public Integer downBalance(GoodDetail goodDetail) {
        Signal signal = new Signal();
        signal.setSymbols("-");
        return iShopGoodDetailMapper.updateBalance(goodDetail, signal);
    }

    @Override
    public Integer addBalance(GoodDetail goodDetail) {
        Signal signal = new Signal();
        signal.setSymbols("+");
        return iShopGoodDetailMapper.updateBalance(goodDetail, signal);
    }

    @Override
    public Result addSellNumAndDownBalance(List<GoodDetail> goodDetails) {
        for (GoodDetail goodDetail : goodDetails) {
            Integer i = downBalance(goodDetail);
            Integer t = iShopGoodInfoService.addSellingNum(goodDetail.getGoodInfo());

            if (i != 1) {
                return Result.failure("减少库存失败！", i);
            }
            if (t != 1) {
                return Result.failure("添加销量失败！", t);
            }
        }
        return Result.success("添加销量成功,减少库存成功！", null);
    }

    @Override
    public Result downSellNumAndAddBalance(List<GoodDetail> goodDetails) {
        for (GoodDetail goodDetail : goodDetails) {
            Integer t = iShopGoodInfoService.downSellingNum(goodDetail.getGoodInfo());
            Integer i = addBalance(goodDetail);

            if (i != 1) {
                return Result.failure("添加库存失败！", i);
            }
            if (t != 1) {
                return Result.failure("减少销量失败！", i);
            }
        }
        return Result.success("添加库存成功，减少销量失败！", null);
    }
}
