package com.lcl.galaxy.microservice.middleground.task.infrastructure.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.lcl.galaxy.microservice.*"})
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    @Bean
    public FeignErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();
    }
}
