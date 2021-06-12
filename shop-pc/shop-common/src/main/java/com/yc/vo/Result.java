package com.yc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//排除空属性在生成的json字符串中
public class Result implements Serializable {

    private Integer code;//返回码  1 success  0 fail
    private String msg;//返回消息
    private Object data;

    private static final long serialVersionUID = -7853336054891892889L;

    public static Result success(String msg, Object data) {
        return new Result(1, msg, data);
    }

    public static Result failure(String msg, Object data) {
        return new Result(0, msg, data);
    }
}
