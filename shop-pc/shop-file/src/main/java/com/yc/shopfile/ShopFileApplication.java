package com.yc.shopfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopFileApplication.class, args);
    }
}
