package com.lcl.galaxy.microservice.integration.service.rpc;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.integration.service.servicebus.endpoint.CustomerStaffEndpoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerStaffIntegrationServiceImpl {

    @Autowired
    private CustomerStaffEndpoint customerStaffEndpoint;

    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystemDTO);
    }
}
