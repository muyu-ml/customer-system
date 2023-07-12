package com.lcl.galaxy.microservice.middleground.customer.service;

public interface CustomerStaffSyncService {

    void syncOutsourcingCustomerStaffsBySystemId(Long systemId);
}
