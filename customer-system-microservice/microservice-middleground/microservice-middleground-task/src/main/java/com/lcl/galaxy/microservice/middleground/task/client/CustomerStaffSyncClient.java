package com.lcl.galaxy.microservice.middleground.task.client;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = ApiConstants.SERVICE_NAME, path = ApiConstants.PREFIX + "/sync",
                fallbackFactory = CustomerStaffSyncClientFallback.class,
                configuration = FeignConfiguration.class)
public interface CustomerStaffSyncClient {

    @RequestMapping(value = "/{systemId}", method = RequestMethod.GET)
    void syncOutsourcingCustomerStaffsBySystemId(@PathVariable("systemId") Long systemId);
}
