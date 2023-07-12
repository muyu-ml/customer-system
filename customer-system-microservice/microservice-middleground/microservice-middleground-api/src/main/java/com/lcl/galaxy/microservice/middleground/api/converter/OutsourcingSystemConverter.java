package com.lcl.galaxy.microservice.middleground.api.converter;

import com.lcl.galaxy.microservice.middleground.api.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.resp.OutsourcingSystemRespVO;
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
