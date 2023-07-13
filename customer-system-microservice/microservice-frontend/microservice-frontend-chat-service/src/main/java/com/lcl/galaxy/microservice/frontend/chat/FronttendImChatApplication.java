package com.lcl.galaxy.microservice.frontend.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.ticket.mapper")
public class FronttendImChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(FronttendImChatApplication.class, args);
    }

}
