package com.lcl.galaxy.distribution.ticket.service;

import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;

public interface ILocalCustomerStaffService {

    void insertLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);
}
