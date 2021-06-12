package com.yc.enums;

import lombok.Getter;

@Getter
public enum CommentsTypeEnum {
    GOOD(1, "好评"), JUSTSOSO(2, "中评"), BAD(3, "差评");
    private Integer code;
    private String message;

    CommentsTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
