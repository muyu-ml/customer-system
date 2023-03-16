package com.lcl.galaxy.integration.service.definition.service;

import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffIntegrationService {

    List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO);
}
