package com.lcl.galaxy.microservice.integration.api;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;

import java.util.List;

public class CustomerStaffIntegrationServiceApiFallback implements CustomerStaffIntegrationServiceApi{
    @Override
    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return null;
    }
}
