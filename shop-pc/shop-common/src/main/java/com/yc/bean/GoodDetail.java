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
 * @create: 2021-06-04 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetail implements Serializable {
    private static final long serialVersionUID = -5547091655631948893L;
    private Integer sizeno;

    private GoodInfo goodInfo;
    private String size;
    private Integer balance;
    private String intro;
    private String showPic;
    private BigDecimal price;

    private List<GoodType> goodTypeList;
    private List<GoodInfo> goodInfoList;

}
