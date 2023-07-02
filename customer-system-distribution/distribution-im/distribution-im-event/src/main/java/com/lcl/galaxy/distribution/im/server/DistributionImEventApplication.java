package com.lcl.galaxy.distribution.im.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.distribution.im.server.mapper")
public class DistributionImEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributionImEventApplication.class, args);
    }

}
