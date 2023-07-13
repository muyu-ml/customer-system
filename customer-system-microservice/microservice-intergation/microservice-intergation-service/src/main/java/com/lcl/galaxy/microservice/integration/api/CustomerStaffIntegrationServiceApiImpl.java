package com.lcl.galaxy.microservice.integration.api;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.integration.esb.endpoint.CustomerStaffEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/integration/staffs")
public class CustomerStaffIntegrationServiceApiImpl {

    @Autowired
    private CustomerStaffEndpoint customerStaffEndpoint;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<PlatformCustomerStaff> fetchCustomerStaffs(@RequestBody OutsourcingSystemDTO outsourcingSystem){
        return customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystem);
    }
}