package com.yc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-04 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodType implements Serializable {
    private static final long serialVersionUID = 5351432863196501211L;
    private Integer tno;
    private String tname;
    private String pic;
    private Integer status;

    private List<GoodInfo> goodInfoList;
}
