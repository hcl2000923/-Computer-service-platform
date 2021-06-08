package com.yc.shopfile.controller;

import com.yc.bean.*;
import com.yc.util.FileUploadUtil;
import com.yc.util.YcConstants;
import com.yc.vo.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class FileUploadController {

    @PostMapping("memberUpload")
    @Transactional
    public Result memberinfoPhoto(Result result, HttpServletRequest req, HttpServletResponse resp, HttpSession httpSession) {
        MemberInfo memberInfo = null;
        try {
            memberInfo = FileUploadUtil.parseRequest(req, MemberInfo.class);
        } catch (Exception e) {
            result.setCode(0);
            result.setMsg("添加异常！");
            e.printStackTrace();
        }

        //远程调用update头像
        int i = 1;

        MemberInfo m = (MemberInfo) httpSession.getAttribute(YcConstants.LOGINUSER);
        if (m != null && i == 1) {
            m.setPhoto(memberInfo.getPhoto());
            httpSession.setAttribute(YcConstants.LOGINUSER, m);
            result.setCode(1);
            result.setMsg("添加成功！");
        } else {
            result.setCode(0);
            result.setMsg("添加失败！");
        }
        return result;
    }

    @Transactional
    @PostMapping("commentUpload")
    public Result commentsPhoto(HttpServletRequest request, HttpServletResponse response, Result result) {
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
