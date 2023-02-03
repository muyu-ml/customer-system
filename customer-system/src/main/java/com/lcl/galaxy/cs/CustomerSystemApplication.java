package com.lcl.galaxy.cs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.cs.mapper")
public class CustomerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerSystemApplication.class, args);
    }

}
