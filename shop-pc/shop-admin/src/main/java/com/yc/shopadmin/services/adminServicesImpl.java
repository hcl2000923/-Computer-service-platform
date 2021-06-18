package com.yc.shopadmin.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.adminInfo;
import com.yc.exception.BizException;
import com.yc.shopadmin.mapper.adminInfoMapper;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class adminServicesImpl implements AdminService {

    @Resource
    private adminInfoMapper adminInfoMapper;



    //登录
    @Override
    public adminInfo adminLogin(adminInfo adminInfo) throws BizException {
        adminInfo dbuser=adminInfoMapper.selectByAdmin(adminInfo);
        if (dbuser==null){
                throw new BizException("用户名或密码错误");
        }
        return dbuser;
    }

    //修改信息
    @Override
    public Integer adminUpdate(adminInfo adminInfo) {
        return adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
    }

    //查看所有管理员
    @Override
    public List<adminInfo> FindAllAdminInfo() {
        return adminInfoMapper.selectAll();
    }

    //新增管理员
    @Override
    public Integer addAdminInfo(adminInfo adminInfo) {
        return adminInfoMapper.insertSelective(adminInfo);
    }

    //删除管理员
    @Override
    public Integer removeAdminInfo(Integer aid) {
        return adminInfoMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public PageInfo<adminInfo> findByPage(adminInfo adminInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());// startPage()方法后面紧跟的是要分页的查询语句
        List<adminInfo> list = adminInfoMapper.selectAll();
        PageInfo<adminInfo> p = new PageInfo<>(list);
        return p;
    }

}
