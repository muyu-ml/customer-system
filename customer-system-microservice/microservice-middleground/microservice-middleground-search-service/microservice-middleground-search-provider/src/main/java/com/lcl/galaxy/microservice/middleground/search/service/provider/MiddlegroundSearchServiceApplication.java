package com.lcl.galaxy.microservice.middleground.search.service.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.search.service.provider.mapper")
@EnableDubbo
public class MiddlegroundSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundSearchServiceApplication.class, args);
    }
}
