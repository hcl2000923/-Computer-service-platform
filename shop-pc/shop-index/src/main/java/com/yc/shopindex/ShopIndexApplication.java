package com.yc.shopindex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopIndexApplication.class, args);
    }

}
