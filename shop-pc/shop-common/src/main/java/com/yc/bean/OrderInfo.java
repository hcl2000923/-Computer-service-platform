package com.yc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -8852651103467235852L;
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
