package com.lcl.galaxy.microservice.middleground.customer.rpc;

import com.lcl.galaxy.microservice.middleground.customer.service.CustomerStaffSyncService;
import com.lcl.galaxy.microservice.middleground.customer.service.ICustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerStaffSyncServiceImpl implements CustomerStaffSyncService {

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {
        customerStaffService.syncGetOutsourcingCustomerStaffBySystemId(systemId);
    }

}

