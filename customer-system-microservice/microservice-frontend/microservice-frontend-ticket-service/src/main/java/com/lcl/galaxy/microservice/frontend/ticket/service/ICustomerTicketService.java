package com.lcl.galaxy.microservice.frontend.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.TccRequest;

/**
 * <p>
 * 客服工单表 服务类
 * </p>
 */
public interface ICustomerTicketService extends IService<CustomerTicket> {

    void tickeTry(TccRequest<AddTicketReqVO> addTicketReqVO);

    void ticketConfirm(TccRequest<String> ticketNo);

    void ticketCencel(TccRequest<String> ticketNo);
}
