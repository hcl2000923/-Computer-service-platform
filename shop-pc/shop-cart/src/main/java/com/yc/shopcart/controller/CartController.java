package com.yc.shopcart.controller;

import com.yc.bean.CartInfo;
import com.yc.bean.MemberInfo;
import com.yc.shopcart.service.IShopCartBiz;
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
 * @create: 2021-06-16 09:03
 */
@RestController
public class CartController {
    @Resource
    private IShopCartBiz iShopCartBiz;

    @GetMapping("findCarts")
    public Result findCarts(@SessionAttribute(required = false) MemberInfo loginUser) {
        if (loginUser == null) {
            return Result.failure("用户未登录,请先登录!", null);
        }
        CartInfo cartInfo = new CartInfo();
        cartInfo.setMemberInfo(loginUser);
        List<CartInfo> list = iShopCartBiz.findThreeTable(cartInfo);
        loginUser.setCartInfoList(list);
        return Result.success("获取购物车信息成功！", list);
    }

    @PostMapping("update")
    public Result update(CartInfo cartInfo, @SessionAttribute(required = false) MemberInfo loginUser) {
        int i = iShopCartBiz.updateNum(cartInfo);
        if (i == 1) {
            CartInfo cart = new CartInfo();
            cart.setMemberInfo(loginUser);
            List<CartInfo> list = iShopCartBiz.findThreeTable(cart);
            loginUser.setCartInfoList(list);
        } else {
            return Result.failure("更新购物车数量失败！", null);
        }
        return Result.success("更新购物车数量成功！", null);
    }

    @PostMapping("deleteOne")
    public Result deleteOne(CartInfo cartInfo, @SessionAttribute(required = false) MemberInfo loginUser) {
        int delete = iShopCartBiz.delete(cartInfo);
        if (delete == 1) {
            CartInfo cart = new CartInfo();
            cart.setMemberInfo(loginUser);
            List<CartInfo> list = iShopCartBiz.findThreeTable(cart);
            loginUser.setCartInfoList(list);
        } else {
            return Result.failure("删除购物车失败！", null);
        }
        return Result.success("删除购物车成功！", null);
    }

    @PostMapping("deleteAll")
    public Result deleteAll(Integer[] cnos, @SessionAttribute(required = false) MemberInfo loginUser) {
        int delete = iShopCartBiz.deleteByCnos(cnos);
        if (delete > 0) {
            CartInfo cart = new CartInfo();
            cart.setMemberInfo(loginUser);
            List<CartInfo> list = iShopCartBiz.findThreeTable(cart);
            loginUser.setCartInfoList(list);
        } else {
            return Result.failure("删除选中购物车失败！", null);
        }
        return Result.success("删除选中购物车成功！", null);
    }
}
