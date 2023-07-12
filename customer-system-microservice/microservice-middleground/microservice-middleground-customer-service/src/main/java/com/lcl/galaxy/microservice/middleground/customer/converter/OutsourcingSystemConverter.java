package com.lcl.galaxy.microservice.middleground.customer.converter;

import com.lcl.galaxy.microservice.middleground.customer.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.microservice.middleground.customer.controller.vo.resp.OutsourcingSystemRespVO;
import com.lcl.galaxy.microservice.middleground.customer.entity.tenant.OutsourcingSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OutsourcingSystemConverter {

    OutsourcingSystemConverter INSTANCE = Mappers.getMapper(OutsourcingSystemConverter.class);

    // V0 -->  Entity
    OutsourcingSystem convertCreateReq(AddOutsourcingSystemReqVO addOutsourcingSystemReqVO);

    OutsourcingSystemRespVO convertResp(OutsourcingSystem outsourcingSystem);

    List<OutsourcingSystemRespVO> convertListResp(List<OutsourcingSystem> entities);
}
