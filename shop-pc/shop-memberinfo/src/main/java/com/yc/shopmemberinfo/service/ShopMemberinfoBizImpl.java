package com.yc.shopmemberinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.shopmemberinfo.dao.ShopMemberinfoMapper;
import com.yc.util.Encrypt;
import com.yc.util.MailUtils;
import com.yc.vo.Page;
import com.yc.vo.Signal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 17:31
 */
@Service
@Transactional
public class ShopMemberinfoBizImpl implements IShopMemberinfoBiz {
    @Resource
    ExecutorService executorService;

    @Resource
    private ShopMemberinfoMapper shopMemberinfoMapper;

    @Override
    public MemberInfo login(MemberInfo memberInfo) throws BizException {
        String pwd = Encrypt.md5(memberInfo.getPwd());
        memberInfo.setPwd(pwd);
        MemberInfo t = shopMemberinfoMapper.selectByUidAndPwd(memberInfo);
        if (t == null) {
            throw new BizException("用户名或密码错误！");
        }
        return t;
    }

    @Override
    public int addMemberInfo(MemberInfo memberInfo) throws BizException {
        int t = shopMemberinfoMapper.addMemberInfo(memberInfo);
        if (t != 1) {
            throw new BizException("注册异常");
        }
        return t;
    }

    @Override
    public int updateAllByMno(MemberInfo memberInfo) throws BizException {
        int t = shopMemberinfoMapper.updateByMnoOrNickName(memberInfo);
        if (t != 1) {
            throw new BizException("设置个人信息异常");
        }
        return t;
    }

    @Override
    public int updatePwdByNickName(MemberInfo memberInfo) throws BizException {
        int t = shopMemberinfoMapper.updateByMnoOrNickName(memberInfo);
        if (t != 1) {
            throw new BizException("重置密码异常");
        }
        return t;
    }

    @Override
    public int updateStatusByMno(MemberInfo memberInfo) {
        if (memberInfo == null) {
            return 0;
        }
        return shopMemberinfoMapper.updateByMnoOrNickName(memberInfo);
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
        PageInfo<MemberInfo> p = new PageInfo<>(list);
        return p;
    }

    @Override
    public void sendMail(MemberInfo memberInfo, String emailValid) {
        executorService.execute(() -> {
            MailUtils.sendMail(memberInfo.getEmail(), "验证码为：" + emailValid, "易易城账号找回密码验证码");
        });
    }

    @Override
    public Integer addPoint(MemberInfo memberInfo, Integer point) {
        Signal signal = new Signal();
        signal.setSymbols("+");
        Integer t = shopMemberinfoMapper.updatePoint(signal, memberInfo, point);
        return t;
    }

    @Override
    public Integer subPoint(MemberInfo memberInfo, Integer point) {
        Signal signal = new Signal();
        signal.setSymbols("-");
        Integer t = shopMemberinfoMapper.updatePoint(signal, memberInfo, point);
        return t;
    }
}
