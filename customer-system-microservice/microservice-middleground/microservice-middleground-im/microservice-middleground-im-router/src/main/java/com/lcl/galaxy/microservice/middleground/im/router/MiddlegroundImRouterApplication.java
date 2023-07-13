package com.lcl.galaxy.microservice.middleground.im.router;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.middleground.im.router.mapper")
public class MiddlegroundImRouterApplication {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundImRouterApplication.class, args);
    }

}
