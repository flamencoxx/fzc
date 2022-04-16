package com.fzc.fzcfutu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 11615
 * 这是早期的时候写的，代码写的很差，后面再改
 */
@SpringBootApplication
@EnableDiscoveryClient
//        (autoRegister = false)
@EnableFeignClients
public class FzcFutuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcFutuApplication.class, args);


    }

}
