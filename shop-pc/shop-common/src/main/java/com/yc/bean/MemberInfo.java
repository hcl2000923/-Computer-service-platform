package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class MemberInfo implements Serializable {
    private static final long serialVersionUID = 1694487819363528333L;
    private Integer mno;
    @NotEmpty(message = "请输入账号！")
    @Length(min = 6, max = 20, message = "用户名必须是6到20个字符")
    private String nickName;

    private String realName;
    @NotEmpty(message = "请输入密码！")
    @Length(min = 6, max = 20, message = "密码必须是6到20个字符")
    private String pwd;

    private String tel;
    @Email(message = "请输入正确的邮箱账号！")
    @NotEmpty(message = "请尽快绑定邮箱！")
    private String email;
    private String idCard;
    private String addr;
    private Integer sex;
    private String bankCard;
    private String photo;
    private BigDecimal restMoney;
    private BigDecimal freezeMoney;
    private BigDecimal getMoney;
    private BigDecimal usefulMoney;
    private Integer moneyPoint;
    private Integer status;
    private List<CartInfo> cartInfoList;
    private List<OrderItemInfo> orderItemInfoList;
}
