package com.lcl.galaxy.outsouring.hangzhou.converter;

import com.lcl.galaxy.outsouring.hangzhou.controller.vo.req.HangzhouCustomerStaffCreateReqVO;
import com.lcl.galaxy.outsouring.hangzhou.controller.vo.req.HangzhouCustomerStaffUpdateReqVO;
import com.lcl.galaxy.outsouring.hangzhou.controller.vo.resp.HangzhouCustomerStaffRespVO;
import com.lcl.galaxy.outsouring.hangzhou.entity.HangzhouCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HangzhouCustomerStaffConverter {

    HangzhouCustomerStaffConverter INSTANCE = Mappers.getMapper(HangzhouCustomerStaffConverter.class);

    //Entity->VO
    List<HangzhouCustomerStaffRespVO> convertListResp(List<HangzhouCustomerStaff> list);
    HangzhouCustomerStaffRespVO convertResp(HangzhouCustomerStaff entity);

    //VO->Entity
    HangzhouCustomerStaff convertCreateReq(HangzhouCustomerStaffCreateReqVO vo);
    HangzhouCustomerStaff convertUpdateReq(HangzhouCustomerStaffUpdateReqVO vo);
}
