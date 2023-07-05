package com.lcl.galaxy.distribution.ticket.converter;

import com.lcl.galaxy.distribution.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.distribution.ticket.entity.CustomerTicket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerTicketConverter {
    CustomerTicketConverter INSTANCE = Mappers.getMapper(CustomerTicketConverter.class);

    //VO->Entity
    CustomerTicket convertVO(AddTicketReqVO event);
}
