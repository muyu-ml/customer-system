package com.lcl.galaxy.microservice.middleground.customer.loadbalancer;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.cloud.nacos.balancer.NacosBalancer;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tag.TagUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class TagLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    @Value("${tag}")
    private String tagValue;

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public TagLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = (ServiceInstanceListSupplier)this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map((serviceInstances) -> this.getInstanceResponse(serviceInstances, tagValue));
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, String tagValue) {
        log.info("进入自定义负载均衡算法");
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        List<ServiceInstance> chooseInstances = filterList(instances, instance -> tagValue.equals(TagUtils.getTag(instance)));

        if(CollUtil.isEmpty(chooseInstances)){
            log.info("没有找到满足需求的实例");
            chooseInstances = instances;
        }

        // 使用 Nacos 提供的加权随机负载均衡
        return new DefaultResponse(NacosBalancer.getHostByRandomWeight3(chooseInstances));
    }

    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if(CollUtil.isEmpty(from)){
            return new ArrayList<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toList());
    }
}
