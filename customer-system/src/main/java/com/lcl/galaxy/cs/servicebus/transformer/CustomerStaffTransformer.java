package com.lcl.galaxy.cs.servicebus.transformer;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer<T> {
    List<CustomerStaff> transformerCustomerStaff(List<T> list);
}
