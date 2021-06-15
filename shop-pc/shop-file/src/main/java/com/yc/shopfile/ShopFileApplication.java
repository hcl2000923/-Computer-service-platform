package com.yc.shopfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//开启Reids会话共享
@EnableRedisHttpSession
@EnableFeignClients
public class ShopFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopFileApplication.class, args);
    }
}
