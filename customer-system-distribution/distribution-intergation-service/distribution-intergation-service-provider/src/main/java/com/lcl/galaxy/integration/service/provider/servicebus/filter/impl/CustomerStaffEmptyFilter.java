package com.lcl.galaxy.integration.service.provider.servicebus.filter.impl;

import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import com.lcl.galaxy.integration.service.provider.servicebus.filter.AbstractCustomerStaffFilter;

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
