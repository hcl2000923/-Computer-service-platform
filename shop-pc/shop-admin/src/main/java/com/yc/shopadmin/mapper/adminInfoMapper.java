package com.yc.shopadmin.mapper;


import com.yc.bean.adminInfo;

import java.util.List;

public interface adminInfoMapper {
    int deleteByPrimaryKey(Integer aid);

    int insertSelective(adminInfo record);

    adminInfo selectByAdmin(adminInfo adminInfo);

    int updateByPrimaryKeySelective(adminInfo adminInfo);

    List<adminInfo> selectAll();
}