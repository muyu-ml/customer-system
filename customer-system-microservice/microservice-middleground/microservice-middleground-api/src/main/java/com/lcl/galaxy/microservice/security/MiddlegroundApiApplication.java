package com.lcl.galaxy.microservice.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.middleground.api.mapper")
public class MiddlegroundApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundApiApplication.class, args);
    }
}
