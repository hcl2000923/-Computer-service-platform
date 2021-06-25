package com.yc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemInfo implements Serializable {
    private static final long serialVersionUID = -3721211042666306035L;
    private Integer itemno;
    //mno
    private MemberInfo memberInfo;
    //ono
    private OrderInfo orderInfo;
    //sizeno
    private GoodDetail goodDetail;
    private BigDecimal money;
    private Integer num;
    private String descr;
    private Integer status;
    private Date sdate;
    private Date rdate;
    private Integer updateStatus;
}
