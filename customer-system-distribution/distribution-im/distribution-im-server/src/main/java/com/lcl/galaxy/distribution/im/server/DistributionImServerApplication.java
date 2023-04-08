package com.lcl.galaxy.distribution.im.server;

import com.lcl.galaxy.distribution.im.server.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.distribution.im.server.mapper")
public class DistributionImServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributionImServerApplication.class, args);
        Server.start();
    }

}
