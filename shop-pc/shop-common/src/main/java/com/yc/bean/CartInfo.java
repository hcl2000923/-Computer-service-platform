package com.yc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartInfo implements Serializable {
    private static final long serialVersionUID = 5101141995243643853L;
    private Integer cno;
    private GoodDetail goodDetail;//sizeno;
    private MemberInfo memberInfo;//mno;
    private Integer num;
}
