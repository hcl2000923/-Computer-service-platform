package com.yc.shopcomment.dao;

import com.yc.bean.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 14:09
 */

public interface ShopCommentMapper {
    //删除操作
    int delete(@Param("commentno") int commentno);

    //添加评论
    int addComment(Comments comments);

    //分页查询  总条数
    List<Comments> findGoodInfoAndCommentsAllAndPage(Comments comments);

}
