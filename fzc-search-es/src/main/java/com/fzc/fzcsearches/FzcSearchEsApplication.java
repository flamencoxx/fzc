package com.fzc.fzcsearches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class FzcSearchEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcSearchEsApplication.class, args);
    }

}
