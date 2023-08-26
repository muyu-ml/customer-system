package com.lcl.galaxy.microservice.frontend.ticket.converter;

import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;
import com.lcl.galaxy.microservice.frontend.ticket.event.TicketGeneratedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerTicketConverter {
    CustomerTicketConverter INSTANCE = Mappers.getMapper(CustomerTicketConverter.class);

    //VO->Entity
    CustomerTicket convertVO(AddTicketReqVO event);

    CustomerTicket convertVO(TicketGeneratedEvent ticketGeneratedEvent);
}
