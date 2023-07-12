package com.lcl.galaxy.microservice.message.service.common.converter;

import com.lcl.galaxy.microservice.message.service.common.entity.ImMessage;
import com.lcl.galaxy.microservice.message.service.definition.domain.ImMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImMessageConverter {

    ImMessageConverter INSTANCE = Mappers.getMapper(ImMessageConverter.class);

    //DTO->Entity
    ImMessage convert(ImMessageDTO dto);

    //Entity->DTO
    ImMessageDTO convertDTO(ImMessage entity);
    List<ImMessageDTO> convertDTOs(List<ImMessage> entities);
}
