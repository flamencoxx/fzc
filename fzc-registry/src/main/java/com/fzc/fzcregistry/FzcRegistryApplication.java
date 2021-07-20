package com.fzc.fzcregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 11615
 */
@SpringBootApplication
@EnableEurekaServer
public class FzcRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzcRegistryApplication.class, args);
    }

}
