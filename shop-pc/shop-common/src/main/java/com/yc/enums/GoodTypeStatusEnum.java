package com.yc.enums;

import lombok.Getter;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-12 08:47
 */
@Getter
public enum GoodTypeStatusEnum {

    UPSHOW(1, "上架类型"), DOWNSHOW(2, "下架类型");
    private Integer code;
    private String message;

    GoodTypeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
