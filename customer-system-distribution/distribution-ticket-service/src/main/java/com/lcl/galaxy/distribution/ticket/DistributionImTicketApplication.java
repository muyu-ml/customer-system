package com.lcl.galaxy.distribution.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.distribution.ticket.mapper")
public class DistributionImTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributionImTicketApplication.class, args);
    }

}
