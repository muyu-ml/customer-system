package com.lcl.galaxy.integration.service.provider.servicebus.filter;

import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;

public interface CustomerStaffFilter {
    PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff);
    void setNext(CustomerStaffFilter customerStaffFilter);
    CustomerStaffFilter getNext();
    CustomerStaffFilter getLast();
}
