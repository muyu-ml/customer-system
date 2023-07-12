package com.lcl.galaxy.microservice.message.service.readwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lcl.galaxy.message.service")
@MapperScan(basePackages = "com.lcl.galaxy.message.service.common.mapper")
public class MessageReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageReadWriteApplication.class, args);
    }

}
