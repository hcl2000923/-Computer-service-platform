package com.yc.shopgood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.shopgood.dao")
public class ShopGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopGoodApplication.class, args);
    }

}
