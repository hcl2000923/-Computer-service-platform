package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo implements Serializable {
    private static final long serialVersionUID = 1694487819363528333L;
    private Integer mno;
    private String nickName;
    private String realName;
    private String pwd;
    private String tel;
    private String email;
    private String idCard;
    private String addr;
    private Integer sex;
    private String bankCard;
    private String photo;
    private BigDecimal restMoney;
    private BigDecimal freezeMoney;
    private BigDecimal getMoney;
    private BigDecimal usefulMoney;
    private Integer moneyPoint;
    private Integer status;

    private List<OrderItemInfo> orderItemInfoList;
}
