package com.yc.shopmemberinfo.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.vo.Page;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 20:33
 */
public interface IShopMemberinfoBiz {
    //登录操作
    public MemberInfo login(MemberInfo memberInfo);

    //添加
    int addMemberInfo(MemberInfo memberInfo) throws BizException;

    //动态更新sql
    int updateAllByMno(MemberInfo memberInfo);

    //更新密码
    int updatePwdByMno(MemberInfo memberInfo);

    //更新状态
    int updateStatusByMno(MemberInfo memberInfo);

    //更新积分
    int updateMoneyPointByMno(MemberInfo memberInfo);

    //查询条数
    Integer getMemberinfoCount(MemberInfo memberInfo);

    //多条件查询
    List<MemberInfo> findAllByTrem(MemberInfo memberInfo);

    //分页查询
    PageInfo<MemberInfo> findByPage(MemberInfo memberInfo, Page page);
}
