package com.lcl.galaxy.distribution.ticket.cache;

import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;

public interface LocalCustomerStaffRedisRepository {

    void saveLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(String staffId);

    LocalCustomerStaff findLocalCustomerStaffByStaffId(String staffId);


}
