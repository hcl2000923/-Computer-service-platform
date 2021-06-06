package com.yc.shopmemberinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.shopmemberinfo.dao.ShopMemberinfoMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 17:31
 */
@Service
public class ShopMemberinfoBizImpl implements IShopMemberinfoBiz {
    @Resource
    private ShopMemberinfoMapper shopMemberinfoMapper;

    @Override
    public MemberInfo login(MemberInfo memberInfo) {
        MemberInfo t = shopMemberinfoMapper.selectByUidAndPwd(memberInfo);
        return t;
    }

    @Override
    public int addMemberInfo(MemberInfo memberInfo) throws BizException {
        if (memberInfo.getPwd() == null && memberInfo.getPwd().length() < 6) {
            throw new BizException("密码为空，或密码长度小于6位！");
        }
        if (memberInfo.getNickName() != null && memberInfo.getNickName().length() < 6) {
            throw new BizException("账号为空，或账号长度小于6位！");
        }
        int t = shopMemberinfoMapper.addMemberInfo(memberInfo);
        if (t == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public int updateAllByMno(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return 0;
        }
        return shopMemberinfoMapper.updateByMno(memberInfo);
    }

    @Override
    public int updatePwdByMno(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return 0;
        }
        return shopMemberinfoMapper.updateByMno(memberInfo);
    }

    @Override
    public int updateStatusByMno(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return 0;
        }
        return shopMemberinfoMapper.updateByMno(memberInfo);
    }

    @Override
    public int updateMoneyPointByMno(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return 0;
        }
        return shopMemberinfoMapper.updateByMno(memberInfo);
    }

    @Override
    public Integer getMemberinfoCount(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return -1;
        }
        return shopMemberinfoMapper.getMemberinfoCount(memberInfo);
    }

    @Override
    public List<MemberInfo> findAllByTrem(MemberInfo memberInfo) {
        return shopMemberinfoMapper.findAllAndPage(memberInfo);
    }

    @Override
    public PageInfo<MemberInfo> findByPage(MemberInfo memberInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<MemberInfo> list = shopMemberinfoMapper.findAllAndPage(memberInfo);
        PageInfo<MemberInfo> p = new PageInfo<MemberInfo>(list);
        return p;
    }
}
