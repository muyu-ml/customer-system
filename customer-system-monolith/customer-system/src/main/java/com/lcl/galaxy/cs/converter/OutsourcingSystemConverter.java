package com.lcl.galaxy.cs.converter;

import com.lcl.galaxy.cs.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.cs.controller.vo.resp.OutsourcingSystemRespVO;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;
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
