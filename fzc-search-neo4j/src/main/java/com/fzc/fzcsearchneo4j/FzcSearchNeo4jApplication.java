package com.fzc.fzcsearchneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author admin
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class FzcSearchNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcSearchNeo4jApplication.class, args);
    }

}
