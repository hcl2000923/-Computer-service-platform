package com.yc.shopcomment.service;

import com.github.pagehelper.PageInfo;
import com.yc.bean.Comments;
import com.yc.vo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IShopCommentBiz {

    //登录操作
    int delete(@Param("commentno") int commentno);

    //添加评论
    int addComment(Comments comments);

    //分页查询  总条数
    List<Comments> findAllByComments(Comments comments);

    //分页查询
    public PageInfo<Comments> findByPage(Comments comments, Page page);

}
