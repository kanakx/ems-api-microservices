package com.ems.emsdataservicespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EmsDataServiceSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsDataServiceSpringApplication.class, args);
    }

}
