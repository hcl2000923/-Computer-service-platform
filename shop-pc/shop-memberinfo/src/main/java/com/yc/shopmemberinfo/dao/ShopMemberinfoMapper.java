package com.yc.shopmemberinfo.dao;

import com.yc.bean.MemberInfo;
import com.yc.vo.Signal;

import java.util.List;

/**
 * @program: shop-pc
 * @description:
 * @author: 作者
 * @create: 2021-06-06 14:09
 */

public interface ShopMemberinfoMapper {

    //登录操作
    MemberInfo selectByUidAndPwd(MemberInfo memberInfo);

    //添加
    int addMemberInfo(MemberInfo memberInfo);

    //动态更新sql
    int updateByMnoOrNickName(MemberInfo memberInfo);

    // @Deprecated若某类或某方法加上该注解之后，表示此方法或类不再建议使用，调用时也会出现删除线，
    // 但并不代表不能用，只是说，不推荐使用，因为还有更好的方法可以调用
    //查询条数  不建议使用可以用下面的函数
    @Deprecated
    Integer getMemberinfoCount(MemberInfo memberInfo);

    //分页查询
    List<MemberInfo> findAllAndPage(MemberInfo memberInfo);

    Integer updatePoint(Signal signal, MemberInfo memberInfo, Integer point);
}
