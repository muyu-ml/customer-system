package com.lcl.galaxy.microservice.frontend.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.ticket.mapper")
public class MicroserviceImTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceImTicketApplication.class, args);
    }

}
