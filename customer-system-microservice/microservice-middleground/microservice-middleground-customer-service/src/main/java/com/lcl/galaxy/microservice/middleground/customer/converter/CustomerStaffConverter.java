package com.lcl.galaxy.microservice.middleground.customer.converter;

import com.lcl.galaxy.microservice.middleground.customer.controller.vo.AddCustomerStaffReqVO;
import com.lcl.galaxy.microservice.middleground.customer.controller.vo.CustomerStaffRespVO;
import com.lcl.galaxy.microservice.middleground.customer.controller.vo.UpdateCustomerStaffReqVO;
import com.lcl.galaxy.microservice.middleground.customer.entity.staff.CustomerStaff;
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
