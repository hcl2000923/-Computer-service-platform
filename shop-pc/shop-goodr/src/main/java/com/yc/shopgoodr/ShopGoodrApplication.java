package com.yc.shopgoodr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.yc.shopgoodr.dao")
@EnableRedisHttpSession
public class ShopGoodrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopGoodrApplication.class, args);
    }

}
