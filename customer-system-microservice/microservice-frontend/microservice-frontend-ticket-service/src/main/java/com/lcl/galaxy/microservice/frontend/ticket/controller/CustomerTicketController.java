package com.lcl.galaxy.microservice.frontend.ticket.controller;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.service.ICustomerTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/customerTickets")
public class CustomerTicketController {

    @Autowired
    private ICustomerTicketService customerTicketService;

    @PostMapping(value = "/")
    Result<Boolean> insertTicket(@RequestBody AddTicketReqVO addTicketReqVO) {

        customerTicketService.insertTicket(addTicketReqVO);
        return Result.success(true);
    }

    @PostMapping(value = "/tmq")
    Result<Boolean> generateTicket(@RequestBody AddTicketReqVO addTicketReqVO) {

        customerTicketService.generateTicket(addTicketReqVO);
        return Result.success(true);
    }

}
