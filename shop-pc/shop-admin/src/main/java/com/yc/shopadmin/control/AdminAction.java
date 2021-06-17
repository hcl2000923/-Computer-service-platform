package com.yc.shopadmin.control;

import com.github.pagehelper.PageInfo;
import com.yc.bean.adminInfo;
import com.yc.exception.BizException;
import com.yc.shopadmin.services.AdminService;
import com.yc.vo.Page;
import com.yc.vo.Result;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminAction {
    @Resource
    private AdminService adminService;

    /**
     * 管理员登录方法
     * @param adminInfo
     * @return
     */
    @PostMapping("adminLogin")
    private Result adminLogin(@Valid adminInfo adminInfo, Errors errors, HttpSession session){
        if(errors.hasFieldErrors("aname")||errors.hasFieldErrors("pwd")){
            return Result.failure("输入错误",errors.getFieldErrors());
        }
        try {
            adminInfo newAdmin=adminService.adminLogin(adminInfo);
            session.setAttribute("loginedUser",newAdmin);
            return Result.success("登录成功",errors.getFieldErrors());
        } catch (BizException e) {
            e.printStackTrace();
            //自定义的验证信息
            errors.reject("accountOrPwderror",e.getMessage());
            return Result.failure(e.getMessage(),errors.getAllErrors());
        }
    }

    @PostMapping("findAllAdmin")
    private Result findAdminAll(){
         List<adminInfo> list = adminService.FindAllAdminInfo();
         return Result.success("获取管理员信息成功",list);
    }

    @RequestMapping("findByPage")
    private Result findByPage(adminInfo adminInfo, Page page){
        PageInfo<adminInfo> list = adminService.findByPage(adminInfo, page);
        return Result.success("分页成功",list);
    }


    @PostMapping("updateAdmin")
    private Result updateAdmin(@Valid adminInfo adminInfo,Errors errors){
        if(errors.hasFieldErrors("aname")||errors.hasFieldErrors("pwd")){
            return Result.failure("充值密码格式不正确",errors.getFieldErrors());
        }
        Integer key = adminService.adminUpdate(adminInfo);
        return Result.success("修改成功",errors.getFieldErrors());
    }




    @GetMapping("sid")
    public String sid(HttpSession session, HttpServletRequest request){
        return session.getId()+""+request.getServerPort();
    }
}
