package com.lcl.galaxy.microservice.im.server;

import com.lcl.galaxy.microservice.im.server.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.microservice.im.server.mapper")
public class MicroserviceImServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceImServerApplication.class, args);
        Server.start();
    }

}
