package com.lcl.galaxy.microservice.middleground.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.lcl.galaxy.microservice"})
public class MiddleGroundTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddleGroundTaskApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
