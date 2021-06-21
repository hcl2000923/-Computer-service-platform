package com.yc.shopmemberinfo.controller;

import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.shopmemberinfo.service.IShopMemberinfoBiz;
import com.yc.util.YcConstants;
import com.yc.vo.Code;
import com.yc.vo.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 21:45
 */

@RestController
public class MemberInfoController {
    @Resource
    private IShopMemberinfoBiz iShopMemberinfoBiz;

    @PostMapping("login")
    public Result login(@Valid MemberInfo memberInfo, Errors errors, HttpSession session) {
        if (errors.hasFieldErrors("nickName") || errors.hasFieldErrors("pwd")) {
            //不带s随机返回一个
            return Result.failure("输入错误", errors.getFieldErrors());
        }
        try {
            MemberInfo loginUser = iShopMemberinfoBiz.login(memberInfo);
            session.setAttribute(YcConstants.LOGINUSER, loginUser);
            return Result.success("登录成功", errors.getFieldError());
        } catch (BizException e) {
            //自定义错误信息
            errors.reject("accountOrPwdError", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @GetMapping("code")
    public Result codeEquals(@Valid Code code, Errors errors, @SessionAttribute String validateCode) {
        if (errors.hasFieldErrors("code")) {
            return Result.failure("验证码为空", errors.getFieldErrors());
        }
        if (validateCode.equalsIgnoreCase(code.getCode())) {
            return Result.success("验证码一致", errors.getFieldError());
        } else {
            errors.reject("NotEuqalsCode", "验证码不一致");
            return Result.failure("验证码不一致", errors.getAllErrors());
        }
    }

    @PostMapping("addPoint")
    public Result addPoint(@RequestParam Integer mno, @RequestParam Integer moneyPoint) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(mno);
        Integer i = iShopMemberinfoBiz.addPoint(memberInfo, moneyPoint);
        if (i > 0) {
            return Result.success("用户积分添加成功！", null);
        } else {
            return Result.failure("用户积分添加失败！", null);
        }
    }

    @PostMapping("emailValid")
    public Result emailValid(@Valid MemberInfo memberInfo, Errors errors, HttpSession session) {
        if (errors.hasFieldErrors("nickName") || errors.hasFieldErrors("email")) {
            return Result.failure("输入错误", errors.getFieldErrors());
        }
        List<MemberInfo> allByTrem = iShopMemberinfoBiz.findAllByTrem(memberInfo);
        if (allByTrem.size() == 0) {
            //用户未绑定邮箱
            errors.reject("AccountEmailError", "用户或邮箱输入错误");
            return Result.failure("用户或邮箱输入错误！", errors.getAllErrors());
        } else {
            //用户已绑定邮箱
            String emailValid = UUID.randomUUID().toString().substring(0, 4);
            session.setAttribute(YcConstants.EMAILVALID, emailValid);
            session.setAttribute(YcConstants.EMAILACCOUNT, memberInfo.getNickName());
            iShopMemberinfoBiz.sendMail(memberInfo, emailValid);
            return Result.success("已发送，等待邮箱验证码！", errors.getFieldError());
        }
    }

    @PostMapping("getAccount")
    public Result getAccountByEmail(@Valid MemberInfo memberInfo, Errors errors1, @Valid Code code, Errors errors, HttpSession session) {
        if (errors.hasFieldErrors("code")) {
            //不带s随机返回一个
            return Result.failure("验证码输入错误", errors.getFieldErrors());
        }
        if (errors1.hasFieldErrors("nickName") || errors1.hasFieldErrors("email")
                || errors1.hasFieldErrors("pwd")) {
            return Result.failure("输入错误", errors.getFieldErrors());
        }
        String valid = (String) session.getAttribute(YcConstants.EMAILVALID);
        if (valid == null) {
            errors.reject("NotAlreadyValid", "对不起，您还未发送验证码！");
            return Result.failure("对不起，您还未发送验证码！", errors.getAllErrors());
        }
        String nickName = (String) session.getAttribute(YcConstants.EMAILACCOUNT);
        if (!nickName.contentEquals(memberInfo.getNickName())) {
            //如果不等于，检查发送验证的账号是否和是当前账号
            errors.reject("AccountNotEquals", "请检查输入账号是否和发送验证的账号一致！");
            return Result.failure("请检查输入账号是否和发送验证的账号一致！", errors.getAllErrors());
        }
        if (!valid.contentEquals(code.getCode())) {
            //验证码，不一致请重新正确的验证码
            errors.reject("ValidNotEquals", "请检查输入验证码是否正确！");
            return Result.failure("请检查输入验证码是否正确！", errors.getAllErrors());
        }
        MemberInfo m = new MemberInfo();
        m.setNickName(memberInfo.getNickName());
        m.setPwd(memberInfo.getPwd());
        try {
            iShopMemberinfoBiz.updatePwdByNickName(m);
            return Result.success("重置密码成功", errors.getFieldError());
        } catch (BizException e) {
            //自定义错误信息
            errors.reject("重置密码失败", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @PostMapping("reg")
    public Result reg(@Valid MemberInfo memberInfo, Errors errors) {
        if (errors.hasFieldErrors("nickName") || errors.hasFieldErrors("pwd")) {
            return Result.failure("输入错误", errors.getFieldErrors());
            //不带s随机返回一个
        }
        MemberInfo memberInfo1 = new MemberInfo();
        memberInfo1.setNickName(memberInfo.getNickName());
        List<MemberInfo> allByTrem = iShopMemberinfoBiz.findAllByTrem(memberInfo1);
        try {
            if (allByTrem.size() == 0) {
                //用户注册
                iShopMemberinfoBiz.addMemberInfo(memberInfo);
                return Result.success("用户注册成功,赶紧登录吧！", errors.getFieldError());
            } else {
                //注册失败
                errors.reject("alreadyReg", "用户名已被注册");
                return Result.failure("用户名已被注册", errors.getAllErrors());
            }
        } catch (BizException e) {
            //自定义错误信息
            errors.reject("regError", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @GetMapping("getLoginUser")
    public Result getLoginUser(@SessionAttribute(required = false) MemberInfo loginUser) {
        if (loginUser != null) {
            return Result.success("登录对象获取成功！", loginUser);
        }
        return Result.failure("登录对象获取失败！", loginUser);
    }

    @GetMapping("logout")
    public ModelAndView logout(ModelAndView mav, HttpSession session) {
        session.invalidate();
        mav.setViewName("redirect:http://127.0.0.1");
        return mav;
    }

    @PostMapping("updateAll")
    public Result updateAll(@Valid MemberInfo memberInfo, Errors errors, HttpSession session) {
        try {
            if (errors.hasFieldErrors("email")) {
                return Result.failure("输入错误", errors.getFieldErrors());
                //不带s随机返回一个
            }
            iShopMemberinfoBiz.updateAllByMno(memberInfo);
            MemberInfo memberInfo1 = new MemberInfo();
            memberInfo1.setMno(memberInfo.getMno());
            List<MemberInfo> allByTrem = iShopMemberinfoBiz.findAllByTrem(memberInfo);
            if (allByTrem.size() != 0) {
                session.setAttribute(YcConstants.LOGINUSER, allByTrem.get(0));
            }
            return Result.success("修改信息成功！", errors.getFieldError());
        } catch (BizException e) {
            //自定义错误信息
            errors.reject("UpdateErrors", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @PostMapping("updatePwd")
    public Result updatePwd(String pwd, String pwd1, HttpSession session) {
        try {
            MemberInfo memberInfo = (MemberInfo) session.getAttribute(YcConstants.LOGINUSER);
            MemberInfo memberInfo1 = new MemberInfo();
            memberInfo1.setNickName(memberInfo.getNickName());
            memberInfo1.setPwd(pwd);
            iShopMemberinfoBiz.login(memberInfo1);
            memberInfo1.setPwd(pwd1);
            iShopMemberinfoBiz.updateAllByMno(memberInfo1);
            session.invalidate();
            return Result.success("修改密码成功！", null);
        } catch (BizException e) {
            return Result.failure(e.getMessage(), null);
        }
    }

    @PostMapping("updatePhoto")
    @Transactional
    public Result updatePhoto(@RequestBody MemberInfo memberInfo) throws BizException {
        int t = iShopMemberinfoBiz.updateAllByMno(memberInfo);
        if (t == 1) {
            return Result.success("添加成功!", null);
        } else {
            return Result.failure("添加失败!", null);
        }
    }
}
