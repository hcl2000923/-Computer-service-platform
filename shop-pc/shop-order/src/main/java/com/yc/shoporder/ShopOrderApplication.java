package com.yc.shoporder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
//开启Reids会话共享
@EnableRedisHttpSession
@EnableFeignClients
@MapperScan("com.yc.shoporder.dao")
public class ShopOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrderApplication.class, args);
    }

    @SuppressWarnings("AlibabaThreadPoolCreation")
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

}
