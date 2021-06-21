package com.yc.shopindex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableFeignClients
//开启Reids会话共享
@EnableRedisHttpSession
public class ShopIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopIndexApplication.class, args);
    }

}
