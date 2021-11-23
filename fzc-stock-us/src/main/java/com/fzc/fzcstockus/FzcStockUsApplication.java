package com.fzc.fzcstockus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 11615
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
//        (autoRegister = false)
public class FzcStockUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcStockUsApplication.class, args);
    }

}
