package com.lcl.galaxy.cs.servicebus.filter;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;

public interface CustomerStaffFilter {
    CustomerStaff execute(CustomerStaff customerStaff);
    void setNext(CustomerStaffFilter customerStaffFilter);
    CustomerStaffFilter getNext();
    CustomerStaffFilter getLast();
}
