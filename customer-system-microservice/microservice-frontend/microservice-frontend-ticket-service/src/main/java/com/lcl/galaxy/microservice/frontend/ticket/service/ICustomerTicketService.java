package com.lcl.galaxy.microservice.frontend.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.galaxy.microservice.frontend.ticket.event.TicketGeneratedEvent;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;

/**
 * <p>
 * 客服工单表 服务类
 * </p>
 */
public interface ICustomerTicketService extends IService<CustomerTicket> {

    void insertTicket(AddTicketReqVO addTicketReqVO) throws BizException;

    // 添加工单记录，响应前端请求
    void generateTicket(AddTicketReqVO addTicketReqVO) throws BizException;

    // 执行工单生成操作：执行本地事务，RocketMQ事务消息回调入口
    void doGenerateTicket(TicketGeneratedEvent ticketGeneratedEvent);
}
