package com.lcl.galaxy.outsouring.beijing.repository;

import com.lcl.galaxy.outsouring.beijing.entity.BeijingCustomerStaff;

import java.util.List;

public interface BeijingCustomerStaffRepository {

    List<BeijingCustomerStaff> findCustomerStaff();

    List<BeijingCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
