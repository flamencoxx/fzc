package com.fzc.fzcuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 11615
 */
@EnableDiscoveryClient
//        (autoRegister = false)
@SpringBootApplication
public class FzcUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcUserApplication.class, args);
    }

}
