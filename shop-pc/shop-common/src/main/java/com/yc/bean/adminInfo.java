package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "请输入用户名！")
    @Length(min = 2, max = 20, message = "用户名必须是2到20个字符")
    private String aname;
    @NotEmpty(message = "请输入密码！")
    @Length(min = 6, max = 20, message = "密码必须是6到20个字符")
    private String pwd;
    private String tel;
}
