package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminInfo {
    private Integer aid;
    private String aname;
    private String pwd;
    private String tel;
}
