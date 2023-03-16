package com.lcl.galaxy.integration.service.provider.servicebus.endpoint;

import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffEndpoint {
    List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystem);
}
