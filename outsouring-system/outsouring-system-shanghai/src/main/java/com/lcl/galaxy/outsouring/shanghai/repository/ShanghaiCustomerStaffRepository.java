package com.lcl.galaxy.outsouring.shanghai.repository;


import com.lcl.galaxy.outsouring.shanghai.entity.ShanghaiCustomerStaff;

import java.util.List;

public interface ShanghaiCustomerStaffRepository {

    List<ShanghaiCustomerStaff> findCustomerStaff();

    List<ShanghaiCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
