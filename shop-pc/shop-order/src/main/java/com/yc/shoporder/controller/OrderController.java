package com.yc.shoporder.controller;

import com.yc.bean.MemberInfo;
import com.yc.bean.OrderInfo;
import com.yc.enums.OrderInfoPayStatusEnum;
import com.yc.exception.BizException;
import com.yc.shoporder.service.IShopOrderInfoBiz;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-15 17:49
 */
@RestController
public class OrderController {
    @Resource
    private IShopOrderInfoBiz iShopOrderInfoBiz;

//    @Resource
//    private IShopOrderItemInfoBiz iShopOrderItemInfoBiz;

    @GetMapping("findOrderByMno")
    public Result findOrderByMno(@SessionAttribute(required = false) MemberInfo loginUser) {
        if (loginUser == null) {
            return Result.failure("用户未登录,请先登录!", null);
        }
        Integer mno = loginUser.getMno();
        List<OrderInfo> list = iShopOrderInfoBiz.findByMno(mno);
        OrderInfo orderInfo = null;
        List<OrderInfo> orderInfoMore = null;
        for (int i = 0; i < list.size(); i++) {
            orderInfo = list.get(i);
            orderInfoMore = iShopOrderInfoBiz.findOneToMany(orderInfo);
            list.set(i, orderInfoMore.get(0));
        }
        return Result.success("获取订单信息成功", list);
    }

    @PostMapping("deleteOrder")
    public Result deleteOrder(OrderInfo orderInfo) {
        if (orderInfo.getOno() == null) {
            return Result.failure("获取购买编号异常", null);
        }
        orderInfo.setStatus(OrderInfoPayStatusEnum.CANCEL.getCode());
        try {
            iShopOrderInfoBiz.update(orderInfo);
            return Result.success("取消订单成功", null);
        } catch (BizException e) {
            return Result.failure(e.getMessage(), null);
        }

    }

}
