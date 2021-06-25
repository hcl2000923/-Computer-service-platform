package com.yc.shopgoodr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.vo.GoodDetailVO;
import com.yc.vo.GoodInfoVO;
import com.yc.vo.Page;
import com.yc.vo.Signal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IShopGoodrInfoServiceImpl implements IShopGoodrInfoService {


    @Resource
    private IShopGoodrInfoMapper iShopGoodrInfoMapper;

    /**
     * 展示查询前六条的数据
     *
     * @return
     */
    @Override
    public List<GoodDetail> finds() {
        return iShopGoodrInfoMapper.findShowSix();
    }

    @Override
    public PageInfo<GoodInfo> findByMultiAndPage(GoodInfo goodInfo, Integer[] tnos, Page page, Signal signal) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GoodInfo> list = iShopGoodrInfoMapper.findByTrem(goodInfo, tnos, signal);
        PageInfo<GoodInfo> p = new PageInfo<>(list);
        return p;
    }

    @Override
    public GoodInfoVO findOneByGno(GoodInfo goodInfo) {
        List<GoodInfo> list = iShopGoodrInfoMapper.findByTrem(goodInfo, null, null);
        GoodInfo gi = list.get(0);

        String picsStr = gi.getPics();
        String[] pics = null;
        if (picsStr != null) {
            pics = picsStr.split(";");
        }
        GoodInfoVO goodInfoVO = new GoodInfoVO();
        goodInfoVO.setGname(gi.getGname());
        goodInfoVO.setGno(gi.getGno());
        goodInfoVO.setGoodType(gi.getGoodType());
        goodInfoVO.setPics(pics);
        goodInfoVO.setPoint(gi.getPoint());
        goodInfoVO.setSellNum(gi.getSellNum());
        List<GoodDetail> goodDetailList = gi.getGoodDetailList();
        List<GoodDetailVO> goodDetailVOList = new ArrayList<>();
        for (int i = 0; i < goodDetailList.size(); i++) {
            GoodDetail goodDetail = goodDetailList.get(i);
            String introStr = goodDetail.getIntro();
            String[] intro = null;
            if (introStr != null) {
                intro = introStr.split(";");
            }
            GoodDetailVO goodDetailVO = new GoodDetailVO();
            goodDetailVO.setBalance(goodDetail.getBalance());
            goodDetailVO.setGoodInfo(goodDetail.getGoodInfo());
            goodDetailVO.setIntro(intro);
            goodDetailVO.setPrice(goodDetail.getPrice());
            goodDetailVO.setShowPic(goodDetail.getShowPic());
            goodDetailVO.setSize(goodDetail.getSize());
            goodDetailVO.setSizeno(goodDetail.getSizeno());
            goodDetailVOList.add(goodDetailVO);
        }
        goodInfoVO.setGoodDetailVOList(goodDetailVOList);
        return goodInfoVO;
    }

}
