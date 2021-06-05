package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetail {
    private Integer sizeno;

    private GoodInfo goodInfo;

    private String size;
    private Integer balance;
    private String intro;
    private String showPic;
    private BigDecimal price;

}
