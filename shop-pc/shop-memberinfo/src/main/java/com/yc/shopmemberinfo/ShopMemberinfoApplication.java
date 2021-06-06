package com.yc.shopmemberinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//开启Reids会话共享
@EnableRedisHttpSession
@EnableEurekaClient
@MapperScan("com.yc.shopmemberinfo.dao")
public class ShopMemberinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMemberinfoApplication.class, args);
    }

}
