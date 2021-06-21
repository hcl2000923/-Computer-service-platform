package com.yc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-18 12:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signal implements Serializable {
    private static final long serialVersionUID = -4089341972333621968L;
    //排序字段类型
    private String sort;
    //符号 +  -
    private String symbols;
}
