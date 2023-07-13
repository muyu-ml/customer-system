package com.lcl.galaxy.microservice.middleground.im.server;

import com.lcl.galaxy.microservice.middleground.im.server.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.middleground.im.server.mapper")
public class MiddlegroundImServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundImServerApplication.class, args);
        Server.start();
    }

}
