package com.yc.shopgoodr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.yc")
@MapperScan("com.yc.shopgoodr.dao")
public class ShopGoodrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopGoodrApplication.class, args);
	}

}
