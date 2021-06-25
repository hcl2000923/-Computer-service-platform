package com.yc.shopcomment.controller;

import com.github.pagehelper.PageInfo;
import com.yc.bean.Comments;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.MemberInfo;
import com.yc.shopcomment.service.IShopCommentBiz;
import com.yc.vo.Page;
import com.yc.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-17 12:09
 */
@RestController
public class CommentController {

    @Resource
    private IShopCommentBiz iShopCommentBiz;

    @PostMapping("add")
    public Result add(HttpServletRequest req, @SessionAttribute MemberInfo loginUser, Comments comments, GoodDetail goodDetail, GoodInfo goodInfo) {
        if (loginUser == null) {
            return Result.failure("用户未登录,请先登录!", null);
        }
        comments.setMemberInfo(loginUser);
        comments.setGoodDetail(goodDetail);
        comments.setGoodInfo(goodInfo);
        //insert
        int i = iShopCommentBiz.addComment(comments);
        if (i == 1) {
            return Result.success("添加评价成功", null);
        } else {
            return Result.failure("添加评价失败", null);
        }
    }

    @PostMapping("upload")
    public Result upload(@RequestPart("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename() + "---" + file.getResource() + "--" + file.getSize());
        return Result.success("success", file);
    }

    @PostMapping("findCommentByType")
    public Result findCommentByType(GoodInfo goodInfo, Page page) {
        List<PageInfo> commentList = new ArrayList();

        Comments comments = new Comments();
        comments.setGoodInfo(goodInfo);
        PageInfo<Comments> all = iShopCommentBiz.findByPage(comments, page);
        commentList.add(all);

        comments.setType(1);
        PageInfo<Comments> good = iShopCommentBiz.findByPage(comments, page);
        commentList.add(good);

        comments.setType(2);
        PageInfo<Comments> normal = iShopCommentBiz.findByPage(comments, page);
        commentList.add(normal);

        comments.setType(3);
        PageInfo<Comments> bad = iShopCommentBiz.findByPage(comments, page);
        commentList.add(bad);

        return Result.success("评论初始化成功", commentList);
    }
}
