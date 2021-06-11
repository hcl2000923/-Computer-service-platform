package com.yc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-11 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
