package com.lcl.galaxy.microservice.middleground.api.intergation;

import com.lcl.galaxy.microservice.integration.api.CustomerStaffIntegrationService;
import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.entity.tenant.OutsourcingSystem;
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
