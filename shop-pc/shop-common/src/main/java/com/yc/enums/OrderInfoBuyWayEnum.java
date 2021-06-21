package com.yc.enums;

import lombok.Getter;

@Getter
public enum OrderInfoBuyWayEnum {
    CASH("现金"), ONLINEPAY("在线支付");
    private String message;

    OrderInfoBuyWayEnum(String message) {
        this.message = message;
    }
}