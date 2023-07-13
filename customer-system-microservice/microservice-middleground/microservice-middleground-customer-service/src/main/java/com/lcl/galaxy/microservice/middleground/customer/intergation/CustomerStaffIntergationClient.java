package com.lcl.galaxy.microservice.middleground.customer.intergation;

import com.lcl.galaxy.microservice.integration.api.CustomerStaffIntegrationServiceApi;
import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.middleground.customer.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.customer.entity.tenant.OutsourcingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntergationClient {

    @Autowired
    private CustomerStaffIntegrationServiceApi customerStaffIntegrationService;

    public List<CustomerStaff> getCustomerStaffs(OutsourcingSystem outsourcingSystem){
        OutsourcingSystemDTO outsourcingSystemDTO = CustomerStaffIntegegrationConverter.INSTANCE.convertOutsourcingSystemDTO(outsourcingSystem);
        List<PlatformCustomerStaff> platformCustomerStaffs = customerStaffIntegrationService.fetchCustomerStaffs(outsourcingSystemDTO);
        return CustomerStaffIntegegrationConverter.INSTANCE.convertOutsourcingSystems(platformCustomerStaffs);
    }

}
