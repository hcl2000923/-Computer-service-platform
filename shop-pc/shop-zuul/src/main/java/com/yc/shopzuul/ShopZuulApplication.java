package com.yc.shopzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableZuulProxy
//开启Reids会话共享
public class ShopZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopZuulApplication.class, args);
    }

}
