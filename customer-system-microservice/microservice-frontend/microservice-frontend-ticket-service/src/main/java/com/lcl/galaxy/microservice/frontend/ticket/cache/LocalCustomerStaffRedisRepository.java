package com.lcl.galaxy.microservice.frontend.ticket.cache;

import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;

public interface LocalCustomerStaffRedisRepository {

    void saveLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(String staffId);

    LocalCustomerStaff findLocalCustomerStaffByStaffId(String staffId);

    void saveEmptyCustomerStaff(String staffId);
}
