package com.yc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page implements Serializable {

    private static final long serialVersionUID = 3325929357951506959L;
    private Integer pageSize;
    private Integer pageNum;
    private String sort;//排序字段类型
    private String symbols;//符号 +  -
}
