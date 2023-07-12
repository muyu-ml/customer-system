package com.lcl.galaxy.cs.client;

import com.lcl.galaxy.microservice.middleground.customer.service.CustomerStaffSyncService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffServiceClient {

    @DubboReference(version = "${customer.service.version}")
    private CustomerStaffSyncService customerStaffSyncService;

    public void syncGetOutsourcingCustomerStaffBySystemId(Long systemId){
        customerStaffSyncService.syncOutsourcingCustomerStaffsBySystemId(systemId);
    }
}
