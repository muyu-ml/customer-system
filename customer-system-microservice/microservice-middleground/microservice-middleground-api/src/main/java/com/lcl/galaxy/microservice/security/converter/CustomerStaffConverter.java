package com.lcl.galaxy.microservice.security.converter;

import com.lcl.galaxy.microservice.security.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.security.controller.vo.AddCustomerStaffReqVO;
import com.lcl.galaxy.microservice.security.controller.vo.CustomerStaffRespVO;
import com.lcl.galaxy.microservice.security.controller.vo.UpdateCustomerStaffReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerStaffConverter {
    CustomerStaffConverter INSTANCE = Mappers.getMapper(CustomerStaffConverter.class);

    // VO -> Entity
    CustomerStaff convertCreateReq(AddCustomerStaffReqVO addCustomerStaffReqVO);
    CustomerStaff convertUpdateReq(UpdateCustomerStaffReqVO updateCustomerStaffReqVO);

    // Entity -> VO
    CustomerStaffRespVO convertResp(CustomerStaff entity);
    List<CustomerStaffRespVO> convertListResp(List<CustomerStaff> entities);
}
