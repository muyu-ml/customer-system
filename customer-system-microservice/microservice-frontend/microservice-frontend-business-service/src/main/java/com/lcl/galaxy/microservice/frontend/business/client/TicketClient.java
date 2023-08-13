package com.lcl.galaxy.microservice.frontend.business.client;

import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "ticket-service")
public interface TicketClient {

    @PostMapping(value = "/customerTickets/try")
    Result<Boolean> ticketTry(@RequestBody TccRequest<AddTicketReqVO> aAddTicketReqVO);

    @PostMapping(value = "/customerTickets/confirm")
    Result<Boolean> ticketConfirm(@RequestBody TccRequest<String> ticketNo);

    @PostMapping(value = "/customerTickets/cancel")
    Result<Boolean> ticketCancel(@RequestBody TccRequest<String> ticketNo);
}
