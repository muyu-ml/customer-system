package com.lcl.galaxy.distribution.im.client;

import com.lcl.galaxy.distribution.im.client.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributionImServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributionImServerApplication.class, args);
        Server.start();
    }

}
