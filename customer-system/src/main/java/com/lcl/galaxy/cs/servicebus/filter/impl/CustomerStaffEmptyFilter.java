package com.lcl.galaxy.cs.servicebus.filter.impl;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.servicebus.filter.AbstractCustomerStaffFilter;

import java.util.Objects;

public class CustomerStaffEmptyFilter extends AbstractCustomerStaffFilter {


    @Override
    public CustomerStaff execute(CustomerStaff customerStaff) {
        if(Objects.isNull(customerStaff.getStaffName())){
            return null;
        }

        if(getNext() != null){
            return getNext().execute(customerStaff);
        }

        return customerStaff;
    }
}
