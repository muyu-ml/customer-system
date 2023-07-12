package com.lcl.galaxy.microservice.middleground.api.rpc;

import com.lcl.galaxy.microservice.middleground.api.service.ICustomerStaffService;
import com.lcl.galaxy.microservice.middleground.customer.service.CustomerStaffSyncService;
//import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

//@DubboService(version = "${customer.service.version}")
public class CustomerStaffSyncServiceImpl implements CustomerStaffSyncService {

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {
        customerStaffService.syncGetOutsourcingCustomerStaffBySystemId(systemId);
    }

}

