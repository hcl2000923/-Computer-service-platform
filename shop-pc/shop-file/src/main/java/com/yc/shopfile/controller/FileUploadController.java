package com.yc.shopfile.controller;

import com.yc.bean.*;
import com.yc.util.FileUploadUtil;
import com.yc.vo.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileUploadController {

    @Resource
    IMemberInfoAction iMemberInfoAction;

    @PostMapping("memberUpload")
    @Transactional(rollbackFor = Exception.class)
    public Result memberinfoPhoto(HttpServletRequest req, @SessionAttribute MemberInfo loginUser) throws Exception {
        MemberInfo memberInfo = null;
        memberInfo = FileUploadUtil.parseRequest(req, MemberInfo.class);
        //远程调用update头像
        Result result = iMemberInfoAction.updatePhoto(memberInfo);
        if (result.getCode() == 1) {
            loginUser.setPhoto(memberInfo.getPhoto());
        }
        return result;
    }

    @Transactional
    @PostMapping("commentUpload")
    public Result commentsPhoto(HttpServletRequest request, Result result) {
        try {
            Comments comments = FileUploadUtil.parseRequest(request, Comments.class);
        } catch (Exception e) {
            result.setCode(0);
            result.setMsg("添加异常！");
            e.printStackTrace();
        }

        //远程调用添加评论
        int i = 1;

        result.setCode(i);
        return result;
    }

    @Transactional
    @PostMapping("goodDetailUpload")
    public Result goodDetailPhoto(HttpServletRequest request, HttpServletResponse response, Result result) {
        try {
            GoodDetail goodDetail = FileUploadUtil.parseRequest(request, GoodDetail.class);
        } catch (Exception e) {
            result.setCode(0);
            result.setMsg("添加异常！");
            e.printStackTrace();
        }

        //远程调用添加评论
        int i = 1;

        result.setCode(i);
        return result;
    }

    @Transactional
    @PostMapping("goodInfoUpload")
    public Result goodInfoPhoto(HttpServletRequest request, HttpServletResponse response, Result result) {
        try {
            GoodInfo goodInfo = FileUploadUtil.parseRequest(request, GoodInfo.class);
        } catch (Exception e) {
            result.setCode(0);
            result.setMsg("添加异常！");
            e.printStackTrace();
        }

        //远程调用添加评论
        int i = 1;

        result.setCode(i);
        return result;
    }

    @Transactional
    @PostMapping("goodTypeUpload")
    public Result goodTypePhoto(HttpServletRequest request, HttpServletResponse response, Result result) {
        try {
            GoodType goodType = FileUploadUtil.parseRequest(request, GoodType.class);
        } catch (Exception e) {
            result.setCode(0);
            result.setMsg("添加异常！");
            e.printStackTrace();
        }

        //远程调用添加评论
        int i = 1;

        result.setCode(i);
        return result;
    }
}
