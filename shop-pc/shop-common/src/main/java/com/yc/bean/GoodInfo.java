package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class GoodInfo implements Serializable {
    private static final long serialVersionUID = -4575392408879121675L;
    private Integer gno;
    private String gname;

    private GoodType goodType;
    private GoodDetail goodDetail;
    private Integer sellNum;
    private Integer point;
    private String pics;
    private Integer WordNum;
    List<GoodDetail> goodDetails;
}
