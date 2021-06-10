package com.yc.enums;

import lombok.Getter;

@Getter
public enum OrderItemInfoStatusEnum {
    GET(1, "签收"), NOTGET(2, "未签收"), CANCEL(3, "取消配送");
    private Integer code;
    private String message;

    OrderItemInfoStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
