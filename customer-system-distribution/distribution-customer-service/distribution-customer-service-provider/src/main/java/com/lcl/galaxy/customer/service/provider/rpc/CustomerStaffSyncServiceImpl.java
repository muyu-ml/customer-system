package com.lcl.galaxy.customer.service.provider.rpc;

import com.lcl.galaxy.search.service.definition.service.CustomerStaffSyncService;
import com.lcl.galaxy.customer.service.provider.service.ICustomerStaffService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "${customer.service.version}")
public class CustomerStaffSyncServiceImpl implements CustomerStaffSyncService {

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {
        customerStaffService.syncGetOutsourcingCustomerStaffBySystemId(systemId);
    }

}

