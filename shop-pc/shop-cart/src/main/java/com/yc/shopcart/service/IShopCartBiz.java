package com.yc.shopcart.service;

import com.yc.bean.CartInfo;

import java.util.List;

public interface IShopCartBiz {

    //添加操作
    int addCartInfo(CartInfo cartInfo);

    //删除操作
    int delete(CartInfo cartInfo);

    //批量删除
    int deleteByCnos(Integer[] cnos);

    //更新购物车数量
    int updateNum(CartInfo cartInfo);

    //查询 多条件动态sql  可分页  连表查询  gooddetail cartinfo goodinfo
    List<CartInfo> findThreeTable(CartInfo cartInfo);

    //批量查询
    List<CartInfo> selectByCnos(Integer[] cnos);


}
