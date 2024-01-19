package com.ems.emsauthservicespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EmsAuthServiceSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsAuthServiceSpringApplication.class, args);
    }

}
