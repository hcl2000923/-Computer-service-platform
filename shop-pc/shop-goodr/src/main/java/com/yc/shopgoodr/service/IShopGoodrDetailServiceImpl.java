package com.yc.shopgoodr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.shopgoodr.dao.IShopGoodrDetailsMapper;
import com.yc.util.StringUtil;
import com.yc.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class IShopGoodrDetailServiceImpl implements IShopGoodrDetailService  {

    @Resource
    private IShopGoodrDetailsMapper iShopGoodrDetailsMapper;

    /**
     * 查询详情
     * @param goodDetail
     * @return
     */
    @Override
    public List<GoodDetail> findBytrem(GoodDetail goodDetail) {
        return iShopGoodrDetailsMapper.findByPage2(goodDetail);
    }

    /**
     * 分页
     * @param goodDetail
     * @param page
     * @return
     */
    @Override
    public PageInfo<GoodDetail> findByPage(GoodDetail goodDetail, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());// startPage()方法后面紧跟的是要分页的查询语句
        List<GoodDetail> list = iShopGoodrDetailsMapper.findByPage2(goodDetail);
        PageInfo<GoodDetail> p = new PageInfo<>(list);
        return p;
    }

    @Override
    public String check(String[] nums, String[] sizenos) {
        if(null==nums||nums.length<=0){
            return "";
        }
        if(null==sizenos||sizenos.length<=0) {
            return "";
        }
        GoodDetail dvo=new GoodDetail();
        for(int i=0;i<sizenos.length;i++) {
            dvo.setSizeno(Integer.parseInt(sizenos[i]));
            List<GoodDetail> list=iShopGoodrDetailsMapper.findByPage2(dvo);
            GoodDetail t=list.get(0);
            Integer balance=t.getBalance();
            if(balance<Integer.parseInt(nums[i])) {
                return t.getGoodInfo().getGname() + "-" + t.getSize() + "库存不够，剩余" + t.getBalance() + "个";
            }
        }
        return "ok";
    }


}
