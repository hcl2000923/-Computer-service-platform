package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemInfo {
    private Integer itemno;
    private MemberInfo member;//mno

    private OrderInfo orderInfo; //ono;
    private GoodDetail goodDetail;//sizeno;
    private GoodInfo goodInfo; //gno;
    private BigDecimal money;
    private Integer num;
    private String descr;
    private Integer status;
    private Date sdate;
    private Date rdate;
    private Integer updateStatus;
}
