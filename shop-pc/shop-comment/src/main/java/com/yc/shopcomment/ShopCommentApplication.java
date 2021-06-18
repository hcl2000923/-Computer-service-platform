package com.yc.shopcomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//开启Reids会话共享
@EnableRedisHttpSession
@MapperScan("com.yc.shopcomment.dao")
public class ShopCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCommentApplication.class, args);
    }

}
