package com.fzc.fzcusershiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author admin
 */
@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
public class FzcUserShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcUserShiroApplication.class, args);
    }

}
