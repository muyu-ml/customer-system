package com.lcl.galaxy.microservice.middleground.api.converter;

import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.AddCustomerStaffReqVO;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.CustomerStaffRespVO;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.UpdateCustomerStaffReqVO;
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
