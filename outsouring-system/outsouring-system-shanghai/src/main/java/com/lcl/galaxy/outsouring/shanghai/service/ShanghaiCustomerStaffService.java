package com.lcl.galaxy.outsouring.shanghai.service;

import com.lcl.galaxy.outsouring.shanghai.entity.ShanghaiCustomerStaff;

import java.util.List;

public interface ShanghaiCustomerStaffService {

    List<ShanghaiCustomerStaff> findAllCustomerStaffs();

    List<ShanghaiCustomerStaff> findCustomerStaffsByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
