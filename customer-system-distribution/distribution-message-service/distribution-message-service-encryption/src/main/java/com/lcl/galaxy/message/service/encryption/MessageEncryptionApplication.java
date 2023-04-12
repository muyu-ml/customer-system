package com.lcl.galaxy.message.service.encryption;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lcl.galaxy.message.service")
@MapperScan(basePackages = "com.lcl.galaxy.message.service.common.mapper")
public class MessageEncryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageEncryptionApplication.class, args);
    }

}
