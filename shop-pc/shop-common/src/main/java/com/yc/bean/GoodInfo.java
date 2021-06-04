package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 20:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodInfo {
    private Integer gno;
    private String gname;

    private GoodType goodType;

    private Integer sellNum;
    private Integer point;
    private String pics;

    private List<GoodDetail> goodDetails;
}
