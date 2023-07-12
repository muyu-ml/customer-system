package com.lcl.galaxy.microservice.im.event;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.im.server.mapper")
public class MicroserviceImEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceImEventApplication.class, args);
    }

}
