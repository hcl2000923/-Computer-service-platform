package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminInfo implements Serializable {
    private static final long serialVersionUID = 7420264579275519624L;
    private Integer aid;
    private String aname;
    private String pwd;
    private String tel;
}
