package com.yc.shopcart.service;

import com.yc.bean.CartInfo;
import com.yc.shopcart.dao.ShopCartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopCartImpl implements IShopCartBiz {

    @Resource
    private ShopCartMapper shopCartMapper;

    @Override
    public int addCartInfo(CartInfo cartInfo) {
        if (cartInfo == null) {
            return 0;
        }
        return shopCartMapper.addCartInfo(cartInfo);
    }

    @Override
    public int delete(CartInfo cartInfo) {
        if (cartInfo == null) {
            return 0;
        }
        return shopCartMapper.delete(cartInfo);
    }

    @Override
    public int deleteByCnos(Integer[] cnos) {
        if (cnos == null || cnos.length == 0) {
            return 0;
        }
        return shopCartMapper.deleteByCnos(cnos);
    }

    @Override
    public int updateNum(CartInfo cartInfo) {
        if (cartInfo == null) {
            return 0;
        }
        return shopCartMapper.updateNum(cartInfo);
    }

    @Override
    public List<CartInfo> findThreeTable(CartInfo cartInfo) {
        return shopCartMapper.findThreeTable(cartInfo);
    }

    @Override
    public List<CartInfo> selectByCnos(Integer[] cnos) {
        if (cnos == null || cnos.length == 0) {
            return null;
        }
        return shopCartMapper.selectByCnos(cnos);
    }
}
