package com.ems.emsapigatewayspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EmsApiGatewaySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsApiGatewaySpringApplication.class, args);
    }

}
