package com.yc.shopmemberinfo.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.vo.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 20:33
 */
public interface IShopMemberinfoBiz {
    //登录操作
    MemberInfo login(MemberInfo memberInfo) throws BizException;

    //添加
    int addMemberInfo(MemberInfo memberInfo) throws BizException;

    //动态更新sql
    int updateAllByMno(MemberInfo memberInfo) throws BizException;

    //更新密码
    int updatePwdByNickName(MemberInfo memberInfo) throws BizException;

    //更新状态
    int updateStatusByMno(MemberInfo memberInfo);

    //查询条数
    @Deprecated
    Integer getMemberinfoCount(MemberInfo memberInfo);

    //多条件查询
    List<MemberInfo> findAllByTrem(MemberInfo memberInfo);

    //分页查询
    PageInfo<MemberInfo> findByPage(MemberInfo memberInfo, Page page);

    void sendMail(MemberInfo memberInfo, String emailValid);

    Integer addPoint(MemberInfo memberInfo, BigDecimal point);

    Integer subPoint(MemberInfo memberInfo, BigDecimal point);
}
