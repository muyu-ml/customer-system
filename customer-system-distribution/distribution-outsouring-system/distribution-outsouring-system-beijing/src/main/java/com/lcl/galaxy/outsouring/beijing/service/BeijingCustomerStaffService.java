package com.lcl.galaxy.outsouring.beijing.service;

import com.lcl.galaxy.outsouring.beijing.entity.BeijingCustomerStaff;

import java.util.List;

public interface BeijingCustomerStaffService {

    List<BeijingCustomerStaff> findAllCustomerStaffs();

    List<BeijingCustomerStaff> findCustomerStaffsByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
