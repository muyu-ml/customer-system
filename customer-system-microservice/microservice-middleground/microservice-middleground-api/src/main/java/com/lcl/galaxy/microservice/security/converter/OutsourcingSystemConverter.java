package com.lcl.galaxy.microservice.security.converter;

import com.lcl.galaxy.microservice.security.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.microservice.security.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.microservice.security.controller.vo.resp.OutsourcingSystemRespVO;
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
