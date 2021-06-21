package com.yc.shoporder;

import com.yc.bean.*;
import com.yc.shoporder.dao.ShopOrderInfoMapper;
import com.yc.shoporder.dao.ShopOrderItemInfoMapper;
import com.yc.shoporder.service.IShopOrderInfoBiz;
import com.yc.shoporder.service.IShopOrderItemInfoBiz;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ShopOrderApplicationTests {
    @Resource
    ShopOrderItemInfoMapper shopOrderItemInfoMapper;
    @Resource
    ShopOrderInfoMapper shopOrderInfoMapper;
    @Resource
    IShopOrderInfoBiz iShopOrderInfoBiz;
    @Resource
    IShopOrderItemInfoBiz iShopOrderItemInfoBiz;

    @Test
    void contextLoads() {
        OrderInfo o = new OrderInfo();
        o.setOno("83ae71ca-3894-48aa-918e-ab7ff415d1ab");
        int f = iShopOrderInfoBiz.buyByCash(o);
        System.out.println(f);

//        MailUtils.sendMail("2236347582@qq.com", "订单号：", "新订单请及时查收");
    }

    @Test
    void finds() {
        OrderInfo o = new OrderInfo();
        o.setOno("42379975-f8c6-4245-ad36-ed5b991945f8");
        o.setBuyWay("在线支付");
        o.setStatus(3);
        List<OrderInfo> op = iShopOrderInfoBiz.find(o, 11);
        System.out.println(op);

//        MailUtils.sendMail("2236347582@qq.com", "订单号：", "新订单请及时查收");
    }

//    @Test
//    @Transactional
//    void findMnoByOno() throws BizException {
//        OrderInfo o = new OrderInfo();
//        o.setOno("202105181442182414");
//        System.out.println(iShopOrderItemInfoBiz.findMnoByOno(o));
//    }

    @Test
    @Transactional
    void addOrderInfo() {
        //insert into orderinfo values(#{ono},#{receiver},2,
        // '在线支付',#{detailAddr},#{tel},#{addr},#{pCode},now())
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOno(UUID.randomUUID().toString());
        orderInfo.setReceiver("huang");
        orderInfo.setDetailAddr("黄村");
        orderInfo.setTel("1513456151");
        orderInfo.setAddr("河北-唐山-");
        orderInfo.setPCode(151131);
        shopOrderInfoMapper.addOrderInfo(orderInfo);
    }

    @Test
    @Transactional
    void update() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOno("e6dbb265-7b88-4dbb-b90c-bd25813af5c5");
        orderInfo.setStatus(1);
        shopOrderInfoMapper.update(orderInfo);
    }

    @Test
    void addOrderItemInfo() {
        List<OrderItemInfo> list = new ArrayList<OrderItemInfo>();
        //(null,#{orderItemInfo.member.mno},#{orderItemInfo.orderInfo.ono},#{orderItemInfo.goodDetail.sizeno},
        //            #{orderItemInfo.goodInfo.gno},#{orderItemInfo.money},#{orderItemInfo.num},#{orderItemInfo.descr},2,
        //            DATE_ADD(CURDATE(),INTERVAL 1 DAY),DATE_ADD(CURDATE(),INTERVAL 3 DAY),1)
        OrderItemInfo orderItemInfo = new OrderItemInfo();

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMno(9);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOno("83ae71ca-3894-48aa-918e-ab7ff415d1ab");

        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setSizeno(27);

        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGno(13);

        orderItemInfo.setMoney(new BigDecimal(999));
        orderItemInfo.setDescr("wu");
        orderItemInfo.setMemberInfo(memberInfo);
        orderItemInfo.setGoodDetail(goodDetail);
        orderItemInfo.setOrderInfo(orderInfo);
        list.add(orderItemInfo);
////////////////////////////////

        OrderItemInfo orderItemInfo1 = new OrderItemInfo();
        orderItemInfo1.setMemberInfo(memberInfo);
        orderItemInfo1.setOrderInfo(orderInfo);
        GoodDetail goodDetail1 = new GoodDetail();
        goodDetail1.setSizeno(29);

        GoodInfo goodInfo1 = new GoodInfo();
        orderItemInfo1.setGoodDetail(goodDetail1);
        orderItemInfo1.setMoney(new BigDecimal(929));
        orderItemInfo1.setDescr("wu");

        list.add(orderItemInfo1);

        int i = shopOrderItemInfoMapper.addOrderItemInfo(list);
        System.out.println(i);
    }

    @Test
//    @Transactional
    void updateOrderItemInfo() {
        OrderItemInfo orderItemInfo = new OrderItemInfo();
        orderItemInfo.setItemno(38);
        orderItemInfo.setDescr("hao");
        int i = shopOrderItemInfoMapper.update(orderItemInfo);
        System.out.println(i);
    }

    @Test
//    @Transactional
    void delete() {
        List<OrderItemInfo> list = new ArrayList<OrderItemInfo>();
        OrderItemInfo orderItemInfo = new OrderItemInfo();
        orderItemInfo.setItemno(36);
        OrderItemInfo orderItemInfo1 = new OrderItemInfo();
        orderItemInfo1.setItemno(37);
        list.add(orderItemInfo);
        list.add(orderItemInfo1);
        int i = shopOrderItemInfoMapper.delete(list);
        System.out.println(i);
    }

    @Test
    @Transactional
    void findMultiOrderItemInfo() {
        OrderItemInfo orderItemInfo = new OrderItemInfo();
        orderItemInfo.setItemno(36);
        List<OrderItemInfo> list = shopOrderItemInfoMapper.findByMulti(orderItemInfo);
        System.out.println(list);
    }

    @Test
    @Transactional
    void findMultiOrderInfo() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOno("202106091947407949");
        List<OrderInfo> list = shopOrderInfoMapper.findOneToMany(orderInfo);
        System.out.println(list);
    }

    @Test
    @Transactional
    void findByMno() {
        List<OrderInfo> list = shopOrderInfoMapper.findByMno(new Integer(9));
        System.out.println(list);
    }

    @Test
    @Transactional
    void find() {
        Integer mno = 9;
        List<OrderInfo> list = iShopOrderInfoBiz.findByMno(mno);
        OrderInfo orderInfo = null;
        List<OrderInfo> orderInfoMore = null;
        for (int i = 0; i < list.size(); i++) {
            orderInfo = list.get(i);
            orderInfoMore = iShopOrderInfoBiz.findOneToMany(orderInfo);
            System.out.println(orderInfoMore);
            list.set(i, orderInfoMore.get(0));
        }
        System.out.println(list);
    }

}
