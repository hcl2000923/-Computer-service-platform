package com.yc.shopcomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yc.shopcomment.dao")
public class ShopCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCommentApplication.class, args);
    }

}
