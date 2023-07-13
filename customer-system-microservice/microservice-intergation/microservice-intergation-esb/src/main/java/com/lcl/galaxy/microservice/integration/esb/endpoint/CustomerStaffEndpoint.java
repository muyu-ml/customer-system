package com.lcl.galaxy.microservice.integration.esb.endpoint;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffEndpoint {
    List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystem);
}
