package com.lcl.galaxy.microservice.middleground.customer.loadbalancer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class CustomerRandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public CustomerRandomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = (ServiceInstanceListSupplier)this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map((serviceInstances) -> {
            return this.getInstanceResponse(serviceInstances);
        });
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        log.info("进入自定义负载均衡算法");
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }
        log.info("执行自定义负载均衡算法");
        int index = ThreadLocalRandom.current().nextInt(instances.size());
        ServiceInstance instance = (ServiceInstance)instances.get(index);
        return new DefaultResponse(instance);
    }
}
