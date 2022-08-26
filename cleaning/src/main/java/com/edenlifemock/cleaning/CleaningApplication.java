package com.edenlifemock.cleaning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CleaningApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleaningApplication.class,args);
    }
}
