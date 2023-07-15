package com.lcl.galaxy.microservice.middleground.customer;

import com.lcl.galaxy.microservice.integration.api.ApiConstants;
import com.lcl.galaxy.microservice.middleground.customer.loadbalancer.TagLoadBalancerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication(scanBasePackages = {"com.lcl.galaxy.microservice"})
@MapperScan("com.lcl.galaxy.microservice.middleground.customer.mapper")
//@LoadBalancerClient(name = ApiConstants.SERVICE_NAME, configuration = RandomLoadBalancerConfig.class)
//@LoadBalancerClient(name = ApiConstants.SERVICE_NAME, configuration = CustomerRandomLoadBalancerConfig.class)
@LoadBalancerClient(name = ApiConstants.SERVICE_NAME, configuration = TagLoadBalancerConfig.class)
public class MiddlegroundCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlegroundCustomerServiceApplication.class, args);
    }
}
