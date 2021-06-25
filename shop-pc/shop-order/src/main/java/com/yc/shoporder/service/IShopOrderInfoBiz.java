package com.yc.shoporder.service;

import com.yc.bean.CartInfo;
import com.yc.bean.MemberInfo;
import com.yc.bean.OrderInfo;
import com.yc.exception.BizException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IShopOrderInfoBiz {

    /**
     * 更新操作
     *
     * @param orderInfo
     * @return
     */
    int update(OrderInfo orderInfo) throws BizException;

    /**
     * 分页连表查询  总条数
     *
     * @param orderInfo
     * @return
     */
    List<OrderInfo> findOneToMany(OrderInfo orderInfo);

    /**
     * 通过mno查询支付订单
     *
     * @param mno
     * @return
     */
    List<OrderInfo> findByMno(Integer mno);

    /**
     * 下定单，一系列事务操作
     *
     * @param orderInfo
     * @param cartInfos
     * @param descr
     * @param loginUser
     * @param session
     * @return
     * @throws BizException
     */
    boolean genOrder(OrderInfo orderInfo, List<CartInfo> cartInfos, String descr, MemberInfo loginUser, HttpSession session) throws BizException;

    /**
     * 线下购买
     *
     * @param orderInfo
     * @return
     */
    Integer buyByCash(OrderInfo orderInfo);

    /**
     * 根绝mno查找订单
     *
     * @param orderInfo
     * @param mno
     * @return
     */
    List<OrderInfo> find(OrderInfo orderInfo, Integer mno);

    Boolean downSellNumAndAddBalance(OrderInfo orderInfo) throws BizException;

    Integer confirmOrder(OrderInfo o) throws BizException;
}
