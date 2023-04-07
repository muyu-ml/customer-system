package com.lcl.galaxy.customer.service.provider.intergation;

import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import com.lcl.galaxy.integration.service.definition.service.CustomerStaffIntegrationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntegrationServiceMock implements CustomerStaffIntegrationService {
    @Override
    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return null;
    }
}
