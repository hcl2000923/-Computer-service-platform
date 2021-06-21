package com.yc.shopcart;

import com.yc.bean.CartInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.MemberInfo;
import com.yc.shopcart.dao.ShopCartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShopCartApplicationTests {

    @Resource
    ShopCartMapper shopCartMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void add() {
        CartInfo cartInfo = new CartInfo();
        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setSizeno(4);
        cartInfo.setGoodDetail(goodDetail);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(3);
        cartInfo.setMemberInfo(memberInfo);
        shopCartMapper.addCartInfo(cartInfo);
    }

    @Test
    void deleteOne() {
        CartInfo cartInfo = new CartInfo();
        shopCartMapper.delete(cartInfo);
    }

    @Test
    void deleteMany() {
        Integer[] i = new Integer[]{46, 47};
        shopCartMapper.deleteByCnos(i);
    }

    @Test
    void updateNum() {
        CartInfo cartInfo = new CartInfo();
        cartInfo.setCno(48);
        cartInfo.setNum(3);
        shopCartMapper.updateNum(cartInfo);
    }

    @Test
    void find() {
        CartInfo cartInfo = new CartInfo();
//        MemberInfo m = new MemberInfo();
//        m.setMno(3);
        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setSizeno(3);
        cartInfo.setGoodDetail(goodDetail);
        List<CartInfo> list = shopCartMapper.findThreeTable(null);
        System.out.println(list);
    }

    @Test
    void findByCnos() {
        Integer[] i = new Integer[]{63, 65};
        List<CartInfo> list = shopCartMapper.selectByCnos(i);
        System.out.println(list);
    }

}
