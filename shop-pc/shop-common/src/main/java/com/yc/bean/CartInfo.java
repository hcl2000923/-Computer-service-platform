package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {
    private Integer cno;
    private GoodDetail goodDetail;//sizeno;
    private MemberInfo memberInfo;//mno;
    private Integer num;
}
