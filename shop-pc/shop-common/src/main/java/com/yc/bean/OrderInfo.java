package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderInfo {
    private String ono;
    private String receiver;
    private Integer status;
    private String buyWay;
    private String detailAddr;
    private String tel;
    private String addr;
    private Integer pCode;
    private Date odate;

    private List<OrderItemInfo> orderItemInfoList;
}
