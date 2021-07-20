package com.fzc.fzcfutu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 11615
 */
@SpringBootApplication
@EnableDiscoveryClient
//        (autoRegister = false)
public class FzcFutuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcFutuApplication.class, args);


    }

}
