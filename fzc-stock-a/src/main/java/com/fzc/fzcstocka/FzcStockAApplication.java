package com.fzc.fzcstocka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author flamenco
 */
@SpringBootApplication()
@EnableAsync
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
public class FzcStockAApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcStockAApplication.class, args);
    }

}
