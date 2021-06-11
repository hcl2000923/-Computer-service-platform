package com.yc.shopmemberinfo.controller;

import com.yc.bean.MemberInfo;
import com.yc.exception.BizException;
import com.yc.shopmemberinfo.service.IShopMemberinfoBiz;
import com.yc.util.Encrypt;
import com.yc.vo.Code;
import com.yc.vo.Result;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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
            return Result.failure("输入错误", errors.getFieldErrors());//不带s随机返回一个
        }
        try {
            String pwd = Encrypt.md5(memberInfo.getPwd());
            memberInfo.setPwd(pwd);
            MemberInfo loginuser = iShopMemberinfoBiz.login(memberInfo);
            session.setAttribute("loginedUser", loginuser);
            return Result.success("登录成功", errors.getFieldError());
        } catch (BizException e) {
            e.printStackTrace();
            //自定义错误信息
            errors.reject("accountOrPwdError", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @GetMapping("code")
    public Result codeEquals(@Valid Code code, Errors errors, HttpSession session) {
        if (errors.hasFieldErrors("code")) {
            return Result.failure("验证码为空", errors.getFieldErrors());//不带s随机返回一个
        }
        String validateCode = (String) session.getAttribute("validateCode");
        if (validateCode.equalsIgnoreCase(code.getCode())) {
            return Result.success("验证码一致", errors.getFieldError());
        } else {
            errors.reject("NotEuqalsCode", "验证码不一致");
            return Result.failure("验证码不一致", errors.getAllErrors());
        }
    }

    @PostMapping("emailValid")
    public Result emailValid(@Valid MemberInfo memberInfo, Errors errors, HttpSession session) {
        if (errors.hasFieldErrors("nickName") || errors.hasFieldErrors("email")) {
            return Result.failure("输入错误", errors.getFieldErrors());//不带s随机返回一个
        }
        List<MemberInfo> allByTrem = iShopMemberinfoBiz.findAllByTrem(memberInfo);
        if (allByTrem.size() == 0) {
            //用户未绑定邮箱
            errors.reject("AccountEmailError", "用户或邮箱输入错误");
            return Result.failure("用户或邮箱输入错误！", errors.getAllErrors());
        } else {
            //用户已绑定邮箱
            String emailValid = UUID.randomUUID().toString().substring(0, 4);
            session.setAttribute("EmailValid", emailValid);
            session.setAttribute("EmailAccount", memberInfo.getNickName());
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
        String valid = (String) session.getAttribute("EmailValid");
        if (valid == null) {
            errors.reject("NotAlreadyValid", "对不起，您还未发送验证码！");
            return Result.failure("对不起，您还未发送验证码！", errors.getAllErrors());
        }
        String nickName = (String) session.getAttribute("EmailAccount");
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
            e.printStackTrace();
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
            e.printStackTrace();
            //自定义错误信a息
            errors.reject("regError", e.getMessage());
            return Result.failure(e.getMessage(), errors.getAllErrors());
        }
    }

    @GetMapping("getLoginedUser")
    public Result getLoginedUser(@SessionAttribute(required = false) MemberInfo loginedUser) {
        return Result.success("登录对象获取成功！", loginedUser);
    }

}
