package com.yc.shopgoodr;

import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgoodr.dao.IShopGoodrDetailsMapper;
import com.yc.shopgoodr.dao.IShopGoodrInfoMapper;
import com.yc.shopgoodr.dao.IShopGoodrTypeMapper;
import com.yc.shopgoodr.service.IShopGoodrDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShopGoodrApplicationTests {

	@Resource
	private IShopGoodrTypeMapper iShopGoodrTypeMapper;

	@Resource
	private IShopGoodrInfoMapper iShopGoodrInfoMapper;

	@Resource
	private IShopGoodrDetailsMapper iShopGoodrDetailsMapper;


	@Resource
	private IShopGoodrDetailService iShopGoodrDetailService;
	@Test
	void contextLoads() {
	}


	@Test
	void TestFindByTrem(){
		GoodType GoodType = new GoodType();
//		GoodType.setTname("电脑维修");
//		GoodType.setPic("1c44b1eb7acciuohbngbfui.png");
		GoodType.setTno(1);
		List<GoodType> byTrem = iShopGoodrTypeMapper.findByTrem(GoodType);
		for (GoodType goodType:byTrem){

			System.out.println(goodType);
		}
	}

	@Test
	void TestInfoFindByTrem(){
		GoodInfo goodInfo = new GoodInfo();
//		goodInfo.setGno(1);
		GoodType goodType = new GoodType();
		goodType.setTno(1);
		goodInfo.setGoodType(goodType);
//		goodInfo.setGname("电脑维修");
		List<GoodInfo> byTrem = iShopGoodrInfoMapper.findByTrem(goodInfo);
		for(GoodInfo goodInfo1:byTrem){
			System.out.println(goodInfo1);
		}
	}


	@Test
	void TestFindShowSix(){
		List<GoodDetail> showSix = iShopGoodrInfoMapper.findShowSix();
		for (GoodDetail goodDetail:showSix){
			System.out.println(goodDetail);
		}
	}

	@Test
	void TestGetCommentsTotal(){
		GoodDetail goodDetail = new GoodDetail();
		GoodInfo goodInfo=new GoodInfo();
		goodInfo.setGno(32);
		goodDetail.setGoodInfo(goodInfo);
		int commentsTotal = iShopGoodrInfoMapper.getCommentsTotal(goodDetail);
		System.out.println(commentsTotal);
	}


	@Test
	void TestFindByPageDetail(){
//		GoodDetail goodDetail = new GoodDetail();
//		GoodType goodType = new GoodType();
//		goodType.setTno(1);
//		goodDetail.setSizeno(1);
////		List<GoodDetail> byPage = iShopGoodrDetailsMapper.findByPage(goodDetail);
////		for(GoodDetail goodDetail1:byPage){
////			System.out.println(goodDetail);
////		}
	}


	@Test
	void test2(){
		GoodDetail goodDetail = new GoodDetail();
//		GoodInfo goodInfo = new GoodInfo();
//		goodDetail.setSizeno(1);
		goodDetail.setSizeno(1);
		List<GoodDetail> byPage = iShopGoodrDetailsMapper.findByPage2(goodDetail);
		System.out.println(byPage);
	}

	@Test
	void TestCheckFindByPage2(){
		String[] nums={"2","1"};
		String[] sizenos={"4","27"};
		String check = iShopGoodrDetailService.check(nums, sizenos);
		System.out.println(check);
	}


}
