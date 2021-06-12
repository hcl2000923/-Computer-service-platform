package com.yc.enums;

import lombok.Getter;

@Getter
public enum OrderItemInfoUpdateStatusEnum {
    NORMALNOTICE(1, "正常备注"), ADDRNOTICE(2, "地址备注"),
    TELNOTICE(3, "电话备注"), RECEIVERNOTICE(4, "收件人备注");
    private Integer code;
    private String message;

    OrderItemInfoUpdateStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
