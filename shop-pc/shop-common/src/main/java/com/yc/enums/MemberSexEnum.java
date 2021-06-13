package com.yc.enums;

import lombok.Getter;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-12 08:40
 */
@Getter
public enum MemberSexEnum {
    MALE(1, "男"), FEMALE(2, "女");
    private Integer code;
    private String message;

    MemberSexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
