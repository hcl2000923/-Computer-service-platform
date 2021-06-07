package com.yc.shopmemberinfo.dao;

import com.yc.bean.MemberInfo;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 14:09
 */

public interface ShopMemberinfoMapper {

    //登录操作
    MemberInfo selectByUidAndPwd(MemberInfo memberInfo);

    //添加
    int addMemberInfo(MemberInfo memberInfo);

    //动态更新sql
    int updateByMno(MemberInfo memberInfo);

    //查询条数  不建议使用可以用下面的函数
    @Deprecated
    Integer getMemberinfoCount(MemberInfo memberInfo);

    //分页查询
    List<MemberInfo> findAllAndPage(MemberInfo memberInfo);

}
