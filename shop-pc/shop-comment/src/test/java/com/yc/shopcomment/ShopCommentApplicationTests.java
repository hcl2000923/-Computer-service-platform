package com.yc.shopcomment;

import com.github.pagehelper.PageInfo;
import com.yc.bean.Comments;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.MemberInfo;
import com.yc.shopcomment.dao.ShopCommentMapper;
import com.yc.shopcomment.service.IShopCommentBiz;
import com.yc.vo.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShopCommentApplicationTests {

    @Resource
    ShopCommentMapper shopCommentMapper;
    @Resource
    IShopCommentBiz iShopCommentBiz;

    @Test
    void contextLoads() {
    }

    @Test
    void findAll() {
        Comments comments = new Comments();
        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGno(2);
        List<Comments> goodInfoAndCommentsAllAndPage = shopCommentMapper.findGoodInfoAndCommentsAllAndPage(comments);
        System.out.println(goodInfoAndCommentsAllAndPage);
    }

    @Test
    void findByPage() {
        Comments comments = new Comments();
        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGno(2);
        Page page = new Page();
        page.setPageNum(2);
        page.setPageSize(2);
        PageInfo<Comments> byPage = iShopCommentBiz.findByPage(comments, page);
        System.out.println(byPage);
    }

    @Test
    void delete() {
        int i = shopCommentMapper.delete(8);
        System.out.println(i);
    }

    @Test
    void addComment() {
        Comments comments = new Comments();

        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setSizeno(6);
        comments.setGoodDetail(goodDetail);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(2);
        comments.setMemberInfo(memberInfo);

        comments.setWord("好");
        comments.setType(1);
        comments.setPic("../shopping_images/eaf1b486-80c0-4c62-a49e-ec971aeb54ee13888.jpg");

        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGno(2);
        comments.setGoodInfo(goodInfo);

        int t = shopCommentMapper.addComment(comments);
        System.out.println(comments);

    }
}
