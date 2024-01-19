package com.ems.emseurekaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EmsEurekaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsEurekaSpringApplication.class, args);
    }

}
