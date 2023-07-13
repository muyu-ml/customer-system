package com.lcl.galaxy.microservice.integration.esb.filter;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;

public interface CustomerStaffFilter {
    PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff);
    void setNext(CustomerStaffFilter customerStaffFilter);
    CustomerStaffFilter getNext();
    CustomerStaffFilter getLast();
}
