package com.fzc.fzcstockus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author 11615
 */
@SpringBootApplication
@EnableDiscoveryClient

//        (autoRegister = false)
public class FzcStockUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcStockUsApplication.class, args);
    }

}
