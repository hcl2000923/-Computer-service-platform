package com.yc.shopadmin.mapper;

import com.github.pagehelper.PageInfo;
import com.yc.bean.MemberInfo;
import com.yc.bean.adminInfo;
import com.yc.shopadmin.services.AdminService;
import com.yc.vo.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class adminInfoMapperTest {

    @Resource
    private adminInfoMapper adminInfoMapper;

    @Resource
    private AdminService adminService;


    //测试登录和修改管理员信息
    @Test
    public void adminInfoMapperTest(){
        System.out.println(adminInfoMapper);
        adminInfo adminInfo = new adminInfo();
        adminInfo.setAid(1);
        adminInfo.setAname("1111");
        adminInfo.setPwd("123");
        adminInfo.setTel("12345678912");
        adminInfo = adminInfoMapper.selectByAdmin(adminInfo);
        System.out.println(adminInfo);
        int result=adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
        System.out.println(result);
    }

    @Test
    public void selectAllTest(){
        List<adminInfo> list =adminInfoMapper.selectAll();
        for(adminInfo adminInfo:list){
            System.out.println(adminInfo);
        }
    }

//    @Test
//    private void adminInfoupdateTest(){
//        adminInfo.setAid(1);
//        adminInfo.setAname("1111");
//        adminInfo.setPwd("123");
//        adminInfo.setTel("12345678912");
//        int result=adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
//        System.out.println(result);
//    }

    @Test
    void findPageBiz() {
        adminInfo m = new adminInfo();
        Page p = new Page();
        p.setPageNum(0);
        p.setPageSize(2);
        PageInfo<adminInfo> byPage = adminService.findByPage(m, p);
        System.out.println(byPage);
    }
}