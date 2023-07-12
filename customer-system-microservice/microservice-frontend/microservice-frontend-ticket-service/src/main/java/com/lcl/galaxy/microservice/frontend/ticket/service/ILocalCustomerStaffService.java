package com.lcl.galaxy.microservice.frontend.ticket.service;

import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;

public interface ILocalCustomerStaffService {

    void insertLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    LocalCustomerStaff findLocalCustomerStaffByStaffId(Long staffId);
}
