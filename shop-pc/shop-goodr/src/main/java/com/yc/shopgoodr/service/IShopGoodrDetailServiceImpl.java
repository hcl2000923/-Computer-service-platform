package com.yc.shopgoodr.service;

import com.yc.bean.GoodDetail;
import com.yc.exception.BizException;
import com.yc.shopgoodr.dao.IShopGoodrDetailsMapper;
import com.yc.vo.Result;
import com.yc.vo.Signal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IShopGoodrDetailServiceImpl implements IShopGoodrDetailService {

    @Resource
    private IShopGoodrDetailsMapper iShopGoodrDetailsMapper;

    @Override
    public Result check(Integer[] nums, Integer[] sizenos) {
        if (null == nums || nums.length <= 0) {
            return Result.failure("无商品数量！", null);
        }
        if (null == sizenos || sizenos.length <= 0) {
            return Result.failure("无提交商品！", null);
        }
        if (sizenos.length != nums.length) {
            return Result.failure("提交数组不一致！", null);
        }
        GoodDetail goodDetail = new GoodDetail();
        Signal signal = new Signal();
        signal.setSymbols(">");
        for (int i = 0; i < sizenos.length; i++) {
            goodDetail.setSizeno(sizenos[i]);
            goodDetail.setBalance(nums[i]);
            List<GoodDetail> list = iShopGoodrDetailsMapper.findAllDetail(goodDetail, signal);
            if (list.isEmpty()) {
                return Result.failure("商品号为：" + sizenos[i] + "的商品库存不足！或无该商品号；不能完成订单提交", null);
            }
        }
        return Result.success("库存数量充足！", null);
    }

    @Override
    public Result findBySizeno(GoodDetail goodDetail) throws BizException {
        List<GoodDetail> allDetail = iShopGoodrDetailsMapper.findAllDetail(goodDetail, null);
        if (allDetail.isEmpty()) {
            throw new BizException("查询评论异常！");
        }
        return Result.success("查询成功！", allDetail.get(0));
    }

}
