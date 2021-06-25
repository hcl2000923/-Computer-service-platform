package com.yc.shopcart.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.yc.bean.CartInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.MemberInfo;
import com.yc.shopcart.service.IShopCartBiz;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-16 09:03
 */
@RestController
@DefaultProperties(defaultFallback = "Fallback")
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
    public Result update(CartInfo cartInfo, @SessionAttribute MemberInfo loginUser) {
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
    public Result deleteOne(CartInfo cartInfo, @SessionAttribute MemberInfo loginUser) {
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

    @PostMapping("saveCnos")
    public Result saveCnos(Integer[] cnos, HttpSession session) {
        if (cnos == null || cnos.length == 0) {
            return Result.failure("下单未选中商品！", null);
        }
        List<CartInfo> cartInfos = iShopCartBiz.selectByCnos(cnos);
        session.setAttribute("cartInfos", cartInfos);
        return Result.success("cartInfos集合保存到session成功！", null);
    }

    @GetMapping("find")
    public Result find(@SessionAttribute(required = false) MemberInfo loginUser, @SessionAttribute(required = false) List<CartInfo> cartInfos) {
        if (loginUser == null) {
            return Result.failure("用户未登录,请先登录!", null);
        }
        if (cartInfos == null || cartInfos.isEmpty()) {
            return Result.failure("下单未选中商品！", null);
        }
        Map map = new HashMap();
        map.put("loginUser", loginUser);
        map.put("cartInfos", cartInfos);
        return Result.success("查找购物车数据成功！", map);

    }

    @PostMapping("deleteAll")
    public Result deleteAll(Integer[] cnos, @SessionAttribute MemberInfo loginUser) {
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

    @PostMapping("deleteCart")
    public Result deleteCart(@RequestParam Integer[] cnos, @RequestBody MemberInfo loginUser) {
        int delete = iShopCartBiz.deleteByCnos(cnos);
        if (delete > 0) {
            CartInfo cart = new CartInfo();
            cart.setMemberInfo(loginUser);
            List<CartInfo> list = iShopCartBiz.findThreeTable(cart);
            return Result.success("删除选中购物车成功！", list);
        } else {
            return Result.failure("无商品提交购买！", null);
        }
    }

    @PostMapping("addCart")
    public Result addCart(@SessionAttribute(required = false) MemberInfo loginUser, GoodDetail goodDetail, CartInfo cartInfo) {
        if (loginUser == null) {
            return Result.failure("用户未登录,请先登录!", null);
        }
        //先判断他购物车中有没有该商品
        cartInfo.setGoodDetail(goodDetail);
        cartInfo.setMemberInfo(loginUser);
        List<CartInfo> list = iShopCartBiz.findThreeTable(cartInfo);
        if (!list.isEmpty()) {
            return Result.failure("购物车已有数据，请到购物车中自行添加！", null);
        }
        //购物车没有-》添加购物车
        int flag = iShopCartBiz.addCartInfo(cartInfo);
        if (flag == 1) {
            CartInfo cart = new CartInfo();
            cart.setMemberInfo(loginUser);
            List<CartInfo> li = iShopCartBiz.findThreeTable(cart);
            loginUser.setCartInfoList(li);
            return Result.success("添加购物车成功！", null);
        } else {
            return Result.failure("添加购物车异常！", null);
        }
    }

    public Result Fallback() {
        return Result.failure("不好意思，服务器正忙！", null);
    }
}
