package com.lcl.galaxy.customer.service.provider.intergation;

import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import com.lcl.galaxy.customer.service.provider.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.integration.service.definition.domain.OutsourcingSystemDTO;
import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerStaffIntegegrationConverter {
    CustomerStaffIntegegrationConverter INSTANCE = Mappers.getMapper(CustomerStaffIntegegrationConverter.class);

    OutsourcingSystemDTO convertOutsourcingSystemDTO(OutsourcingSystem outsourcingSystem);


    List<CustomerStaff> convertOutsourcingSystems(List<PlatformCustomerStaff> outsourcingSystemDTO);

}
