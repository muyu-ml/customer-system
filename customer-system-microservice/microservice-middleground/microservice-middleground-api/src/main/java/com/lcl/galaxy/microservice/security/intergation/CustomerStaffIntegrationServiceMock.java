package com.lcl.galaxy.microservice.security.intergation;

import com.lcl.galaxy.microservice.integration.api.CustomerStaffIntegrationService;
import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntegrationServiceMock implements CustomerStaffIntegrationService {
    @Override
    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return null;
    }
}
