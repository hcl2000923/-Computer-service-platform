package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {
    private Integer mno;
    private String nickName;
    private String realName;

    private List<OrderItemInfo> orderItemInfoList;
}
