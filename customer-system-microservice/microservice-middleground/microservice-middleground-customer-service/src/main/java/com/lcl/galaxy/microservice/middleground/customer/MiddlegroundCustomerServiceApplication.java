package com.lcl.galaxy.microservice.middleground.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lcl.galaxy.microservice"})
@MapperScan("com.lcl.galaxy.microservice.middleground.customer.mapper")
public class MiddlegroundCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundCustomerServiceApplication.class, args);
    }
}
