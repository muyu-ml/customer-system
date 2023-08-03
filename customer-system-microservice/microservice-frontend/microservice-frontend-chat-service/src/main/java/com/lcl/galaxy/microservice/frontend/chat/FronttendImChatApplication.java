package com.lcl.galaxy.microservice.frontend.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.lcl.galaxy.microservice.frontend.chat","com.lcl.galaxy.microservice.service"})
@MapperScan("com.lcl.galaxy.microservice.frontend.chat.mapper")
@EnableJpaRepositories("com.lcl.galaxy.microservice.service.repository")
@EntityScan("com.lcl.galaxy.microservice.service.domain")
public class FronttendImChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(FronttendImChatApplication.class, args);
    }

}
