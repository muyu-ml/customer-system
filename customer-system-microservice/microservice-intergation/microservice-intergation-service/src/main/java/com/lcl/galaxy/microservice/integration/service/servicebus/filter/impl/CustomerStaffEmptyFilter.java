package com.lcl.galaxy.microservice.integration.service.servicebus.filter.impl;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.integration.service.servicebus.filter.AbstractCustomerStaffFilter;

import java.util.Objects;

public class CustomerStaffEmptyFilter extends AbstractCustomerStaffFilter {


    @Override
    public PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff) {
        if(Objects.isNull(customerStaff.getStaffName())){
            return null;
        }

        if(getNext() != null){
            return getNext().execute(customerStaff);
        }

        return customerStaff;
    }
}
