package com.lcl.galaxy.outsouring.beijing.converter;

import com.lcl.galaxy.outsouring.beijing.controller.vo.req.BeijingCustomerStaffCreateReqVO;
import com.lcl.galaxy.outsouring.beijing.controller.vo.req.BeijingCustomerStaffUpdateReqVO;
import com.lcl.galaxy.outsouring.beijing.controller.vo.resp.BeijingCustomerStaffRespVO;
import com.lcl.galaxy.outsouring.beijing.entity.BeijingCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BeijingCustomerStaffConverter {

    BeijingCustomerStaffConverter INSTANCE = Mappers.getMapper(BeijingCustomerStaffConverter.class);

    //Entity->VO
    List<BeijingCustomerStaffRespVO> convertListResp(List<BeijingCustomerStaff> list);
    BeijingCustomerStaffRespVO convertResp(BeijingCustomerStaff entity);

    //VO->Entity
    BeijingCustomerStaff convertCreateReq(BeijingCustomerStaffCreateReqVO vo);
    BeijingCustomerStaff convertUpdateReq(BeijingCustomerStaffUpdateReqVO vo);
}
