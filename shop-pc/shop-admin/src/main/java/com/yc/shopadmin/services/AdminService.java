package com.yc.shopadmin.services;


import com.github.pagehelper.PageInfo;
import com.yc.bean.adminInfo;
import com.yc.exception.BizException;
import com.yc.vo.Page;

import java.util.List;


public interface AdminService {

    //管理员登录
    adminInfo adminLogin(adminInfo adminInfo) throws BizException;

    //管理员信息修改
    Integer adminUpdate(adminInfo adminInfo);

    //查看所有管理员
    List<adminInfo> FindAllAdminInfo();

    //添加管理员
    Integer addAdminInfo(adminInfo adminInfo);

    //删除管理员
    Integer removeAdminInfo(Integer aid);

    //分页查询
    PageInfo<adminInfo> findByPage(adminInfo adminInfo, Page page);
}
