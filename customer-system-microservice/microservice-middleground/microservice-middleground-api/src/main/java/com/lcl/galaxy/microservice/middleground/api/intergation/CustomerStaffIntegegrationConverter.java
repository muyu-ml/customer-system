package com.lcl.galaxy.microservice.middleground.api.intergation;

import com.lcl.galaxy.microservice.integration.api.dto.OutsourcingSystemDTO;
import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.entity.tenant.OutsourcingSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerStaffIntegegrationConverter {
    CustomerStaffIntegegrationConverter INSTANCE = Mappers.getMapper(CustomerStaffIntegegrationConverter.class);

    OutsourcingSystemDTO convertOutsourcingSystemDTO(OutsourcingSystem outsourcingSystem);


    List<CustomerStaff> convertOutsourcingSystems(List<PlatformCustomerStaff> outsourcingSystemDTO);

}
