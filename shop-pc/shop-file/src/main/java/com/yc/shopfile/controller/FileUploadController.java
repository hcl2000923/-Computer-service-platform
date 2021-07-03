package com.yc.shopfile.controller;

import com.yc.bean.*;
import com.yc.util.FileUploadUtil;
import com.yc.shopfile.utils.FastDFSClient;
import com.yc.shopfile.utils.FileUtils;
import com.yc.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileUploadController {
    @Resource
    FastDFSClient fastDFSClient;
    @Value("${fastDFS}")
    String fastDFS;

    @PostMapping("memberUpload")
    @Transactional(rollbackFor = Exception.class)
    public Result memberinfoPhoto(HttpServletRequest req, @SessionAttribute MemberInfo loginUser) throws Exception {
        MemberInfo memberInfo = FileUploadUtil.parseRequest(req, MemberInfo.class);
        String photo =memberInfo.getPhoto();
        MultipartFile multipartFile= FileUtils.fileToMultipart(photo);
        String url=fastDFSClient.uploadBase64(multipartFile);
        return Result.success("上传成功！",fastDFS+url);
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
