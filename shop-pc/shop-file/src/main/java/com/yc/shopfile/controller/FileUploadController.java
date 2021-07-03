package com.yc.shopfile.controller;

import com.yc.util.FileUploadUtil;
import com.yc.shopfile.utils.FastDFSClient;
import com.yc.shopfile.utils.FileUtils;
import com.yc.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
public class FileUploadController {
    @Resource
    FastDFSClient fastDFSClient;
    @Value("${fastDFS}")
    String fastDFS;

    @PostMapping("upload")
    @Transactional(rollbackFor = Exception.class)
    public Result memberinfoPhoto(HttpServletRequest req) throws Exception {
        String photo = FileUploadUtil.parseRequest(req);
        MultipartFile multipartFile= FileUtils.fileToMultipart(photo);
        String url=fastDFSClient.uploadBase64(multipartFile);
        return Result.success("上传成功！",fastDFS+url);
    }
}
