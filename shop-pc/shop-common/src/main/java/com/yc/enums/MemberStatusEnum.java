package com.yc.enums;

import lombok.Getter;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-12 08:42
 */
@Getter
public enum MemberStatusEnum {
    NORMAL(1, "正常"), FREEZED(2, "冻结");
    private Integer code;
    private String message;

    MemberStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}