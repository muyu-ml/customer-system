package com.lcl.galaxy.microservice.middleground.customer.loadbalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagLoadBalancerConfig {
    // 重定义ReactorServiceInstanceLoadBalancer接口
    @Bean
    public ReactorServiceInstanceLoadBalancer tagLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        //返回随机轮询负载均衡方式
        return new TagLoadBalancer(serviceInstanceListSupplierProvider);
    }
}
