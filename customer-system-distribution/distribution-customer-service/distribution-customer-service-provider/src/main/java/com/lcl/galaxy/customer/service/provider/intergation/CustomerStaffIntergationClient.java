package com.lcl.galaxy.customer.service.provider.intergation;

import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import com.lcl.galaxy.customer.service.provider.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import com.lcl.galaxy.integration.service.definition.service.CustomerStaffIntegrationService;
//import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntergationClient {
    //@DubboReference(version = "${integration.service.version}", timeout = 5000, loadbalance = "roundrobin", retries = 2, mock = "com.lcl.galaxy.customer.service.provider.intergation.CustomerStaffIntegrationServiceMock")
    private CustomerStaffIntegrationService customerStaffIntegrationService;

    public List<CustomerStaff> getCustomerStaffs(OutsourcingSystem outsourcingSystem){
        OutsourcingSystemDTO outsourcingSystemDTO = CustomerStaffIntegegrationConverter.INSTANCE.convertOutsourcingSystemDTO(outsourcingSystem);
        List<PlatformCustomerStaff> platformCustomerStaffs = customerStaffIntegrationService.fetchCustomerStaffs(outsourcingSystemDTO);
        return CustomerStaffIntegegrationConverter.INSTANCE.convertOutsourcingSystems(platformCustomerStaffs);
    }

}
