package com.yc.shopmemberinfo;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yc.bean.MemberInfo;
import com.yc.shopmemberinfo.dao.ShopMemberinfoMapper;
import com.yc.shopmemberinfo.service.IShopMemberinfoBiz;
import com.yc.vo.Page;
import com.yc.vo.Signal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShopMemberinfoApplicationTests {

    @Resource
    ShopMemberinfoMapper shopMemberinfo;

    @Resource
    IShopMemberinfoBiz iShopMemberinfoBiz;

    @Test
    void selectByUidAndPwd() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setNickName("hcl123456");
        memberInfo.setPwd("qwe");
        MemberInfo memberInfo1 = shopMemberinfo.selectByUidAndPwd(memberInfo);
        Gson gson = new Gson();
        System.out.println(gson.toJson(memberInfo1));
        System.out.println(memberInfo1);
    }

    @Test
    void addUser() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPwd("afdf");
        memberInfo.setNickName("dfa1");
        int i = shopMemberinfo.addMemberInfo(memberInfo);
        System.out.println(memberInfo);
    }

    @Test
    void updateUser() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setRealName("fdaffds");
//        memberInfo.setMno(8);
        memberInfo.setNickName("hcl");
        int i = shopMemberinfo.updateByMnoOrNickName(memberInfo);
        System.out.println(i);
    }

    @Test
    void total() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setStatus(1);
        Integer memberinfoCount = shopMemberinfo.getMemberinfoCount(memberInfo);
        System.out.println(memberinfoCount);
    }

    @Test
    void findPage() {
        MemberInfo memberInfo = new MemberInfo();
//        memberInfo.setMno(8);
        Integer memberinfoCount = shopMemberinfo.getMemberinfoCount(memberInfo);
        System.out.println(memberinfoCount);
    }

    @Test
    void findAll() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(8);
        List<MemberInfo> allAndPage = shopMemberinfo.findAllAndPage(memberInfo);
        System.out.println(allAndPage);
    }

    @Test
    void findPageBiz() {
        MemberInfo m = new MemberInfo();
        Page p = new Page();
        p.setPageNum(2);
        p.setPageSize(2);
        PageInfo<MemberInfo> byPage = iShopMemberinfoBiz.findByPage(m, p);
        System.out.println(byPage);
    }

    @Test
    void updatePoint1() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(11);
        Signal signal = new Signal();
        signal.setSymbols("+");
        System.out.println(shopMemberinfo.updatePoint(signal, memberInfo, 100));
    }
}
