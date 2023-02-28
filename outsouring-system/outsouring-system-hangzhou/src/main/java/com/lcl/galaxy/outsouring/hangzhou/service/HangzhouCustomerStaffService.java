package com.lcl.galaxy.outsouring.hangzhou.service;

import com.lcl.galaxy.outsouring.hangzhou.entity.HangzhouCustomerStaff;

import java.util.Date;
import java.util.List;

public interface HangzhouCustomerStaffService {

    List<HangzhouCustomerStaff> findAllCustomerStaffs();

    List<HangzhouCustomerStaff> findCustomerStaffsByUpdatedTime(Date updatedTime);

    HangzhouCustomerStaff createCustomerStaff(HangzhouCustomerStaff customerStaff);

    HangzhouCustomerStaff updateCustomerStaff(HangzhouCustomerStaff customerStaff);

    HangzhouCustomerStaff deleteCustomerStaffById(Long id);
}
