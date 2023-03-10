package com.lcl.galaxy.cs.servicebus.endpoint;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;

import java.util.List;

public interface CustomerStaffEndpoint {
    List<CustomerStaff> fetchCustomerStaffs(OutsourcingSystem outsourcingSystem);
}
