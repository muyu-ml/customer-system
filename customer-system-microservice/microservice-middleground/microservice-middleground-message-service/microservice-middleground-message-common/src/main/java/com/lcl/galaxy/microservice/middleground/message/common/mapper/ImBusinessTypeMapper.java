package com.lcl.galaxy.microservice.middleground.message.common.mapper;
import com.lcl.galaxy.microservice.middleground.message.common.entity.ImBusinessType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImBusinessTypeMapper{

    ImBusinessType findBusinessTypeByCode(@Param("businessTypeCode") String businessTypeCode);
}
