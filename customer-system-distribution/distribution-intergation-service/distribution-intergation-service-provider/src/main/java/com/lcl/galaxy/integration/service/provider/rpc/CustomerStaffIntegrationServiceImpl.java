package com.lcl.galaxy.integration.service.provider.rpc;

import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import com.lcl.galaxy.integration.service.definition.service.CustomerStaffIntegrationService;
import com.lcl.galaxy.integration.service.provider.servicebus.endpoint.CustomerStaffEndpoint;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "${integration.service.version}")
public class CustomerStaffIntegrationServiceImpl implements CustomerStaffIntegrationService {

    @Autowired
    private CustomerStaffEndpoint customerStaffEndpoint;

    @Override
    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystemDTO);
    }
}
