package com.lcl.galaxy.microservice.middleground.task.client;

public class CustomerStaffSyncClientFallback implements CustomerStaffSyncClient{

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {
        return;
    }
}
