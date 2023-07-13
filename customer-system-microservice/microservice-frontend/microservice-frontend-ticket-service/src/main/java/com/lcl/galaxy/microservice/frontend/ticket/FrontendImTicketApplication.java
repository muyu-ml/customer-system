package com.lcl.galaxy.microservice.frontend.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.frontend.ticket.mapper")
public class FrontendImTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendImTicketApplication.class, args);
    }

}
