package com.yc.shopcomment.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.Comments;
import com.yc.shopcomment.dao.ShopCommentMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopCommentBizImpl implements IShopCommentBiz {

    @Resource
    private ShopCommentMapper shopCommentMapper;

    @Override
    public int delete(int commentno) {
        return shopCommentMapper.delete(commentno);
    }

    @Override
    public int addComment(Comments comments) {
        if (comments != null) {
            return 0;
        }
        return shopCommentMapper.addComment(comments);
    }

    @Override
    public List<Comments> findAllByComments(Comments comments) {
        return shopCommentMapper.findGoodInfoAndCommentsAllAndPage(comments);
    }

    @Override
    public PageInfo<Comments> findByPage(Comments comments, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Comments> list = shopCommentMapper.findGoodInfoAndCommentsAllAndPage(comments);
        PageInfo<Comments> p = new PageInfo<Comments>(list);
        return p;
    }
}
