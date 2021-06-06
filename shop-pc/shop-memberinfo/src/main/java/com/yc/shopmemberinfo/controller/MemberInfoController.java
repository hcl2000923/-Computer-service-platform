package com.yc.shopmemberinfo.controller;

import com.yc.bean.MemberInfo;
import com.yc.shopmemberinfo.service.IShopMemberinfoBiz;
import com.yc.vo.Result;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
            MemberInfo loginuser = iShopMemberinfoBiz.login(memberInfo);
            session.setAttribute("loginedUser", loginuser);
            return Result.success("登录成功", errors.getFieldError());
        } catch (Exception e) {
            e.printStackTrace();
            //自定义错误信息
            errors.reject("accountOrPwdError", e.getMessage());
            return Result.success(e.getMessage(), errors.getAllErrors());
        }
    }

    @GetMapping("getLoginedUser")
    public Result getLoginedUser(@SessionAttribute(required = false) MemberInfo loginedUser) {
        return Result.success("登录对象获取成功！", loginedUser);
    }

}
