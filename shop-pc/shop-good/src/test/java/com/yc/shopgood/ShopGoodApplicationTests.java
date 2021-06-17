package com.yc.shopgood;

import com.yc.bean.GoodDetail;
import com.yc.bean.GoodInfo;
import com.yc.bean.GoodType;
import com.yc.shopgood.dao.IShopGoodDetailMapper;
import com.yc.shopgood.dao.IShopGoodInfoMapper;
import com.yc.shopgood.dao.IShopGoodTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;


@SpringBootTest
class ShopGoodApplicationTests {

	@Resource
	private IShopGoodDetailMapper iShopGoodDetailMapper;

	@Resource
	private IShopGoodTypeMapper iShopGoodTypeMapper;

	@Resource
	private IShopGoodInfoMapper iShopGoodInfoMapper;

	@Test
	void contextLoads() {
	}



	@Test
	void TestAddGoodType(){
		GoodType GoodType = new GoodType();
		GoodType.setTname("装机员2");
		GoodType.setPic("1c44b1eb7acciuohbngbfui.png");
		int Result=iShopGoodTypeMapper.addGoodType(GoodType);
		System.out.println(Result);
	}


	@Test
	void TestAddGoodInfo(){
		GoodInfo goodInfo = new GoodInfo();
		goodInfo.setGname("数据恢复");
		GoodType goodType = new GoodType();
		goodType.setTno(23);
		goodInfo.setGoodType(goodType);
		goodInfo.setPoint(8);
		goodInfo.setPics("../shopping_images/5923502d-b710-4d71-a2ff-bb2357b529ebqweqe.png");
		int i = iShopGoodInfoMapper.addGoodInfo(goodInfo);
		System.out.println(i);
	}


	@Test
	void TestUpdateGoodInfo(){
		GoodInfo goodInfo = new GoodInfo();
		goodInfo.setGno(33);
		goodInfo.setSellNum(100);
		GoodType goodType = new GoodType();
		goodType.setTno(22);
		goodInfo.setGoodType(goodType);
		goodInfo.setPoint(7);
		goodInfo.setPics("../shopping_images/5923502d-b710-4d71-a2ff-bb2357b529ebqweqe.png1");
		int update = iShopGoodInfoMapper.update(goodInfo);
		System.out.println(update);
	}




	@Test
	void TestUpdate(){
		GoodDetail goodDetail = new GoodDetail();
		goodDetail.setBalance(99999);
		goodDetail.setPrice(new BigDecimal(2222222));
		goodDetail.setIntro("非常好");
		goodDetail.setShowPic("../shopping_images/qweqweqwe.png2222");
		goodDetail.setSizeno(60);
		int update = iShopGoodDetailMapper.update(goodDetail);
		System.out.println(update);
	}
	@Test
//	@Transactional
	void TestAddGoodDetail(){
		GoodDetail goodDetail = new GoodDetail();
		GoodInfo goodInfo = new GoodInfo();
		goodInfo.setGno(2);
		goodDetail.setGoodInfo(goodInfo);
		goodDetail.setSize("4266MZ");
		goodDetail.setBalance(9999);
		goodDetail.setIntro("傅;tel:16155416411");
		goodDetail.setShowPic("../shopping_images/qweqweqwe.png");
		goodDetail.setPrice(new BigDecimal(1111));
		int result=iShopGoodDetailMapper.addGoodDetail(goodDetail);
		System.out.println(result);
	}


}
