package com.yc.enums;

import lombok.Getter;

@Getter
public enum OrderInfoPayStatusEnum {
    PAY(1, "已支付"), NOPAY(2, "未支付"), CANCEL(3, "取消订单");
    private Integer code;
    private String message;

    OrderInfoPayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
