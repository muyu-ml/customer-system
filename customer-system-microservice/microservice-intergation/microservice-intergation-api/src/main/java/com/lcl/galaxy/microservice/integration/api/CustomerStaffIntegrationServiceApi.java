package com.lcl.galaxy.microservice.integration.api;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = ApiConstants.SERVICE_NAME, path = ApiConstants.PREFIX+"/staffs",
        fallbackFactory = CustomerStaffIntegrationServiceApiFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomerStaffIntegrationServiceApi {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    List<PlatformCustomerStaff> fetchCustomerStaffs(@RequestBody OutsourcingSystemDTO outsourcingSystemDTO);
}
