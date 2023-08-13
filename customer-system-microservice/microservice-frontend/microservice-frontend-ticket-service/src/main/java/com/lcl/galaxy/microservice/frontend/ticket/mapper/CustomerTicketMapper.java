package com.lcl.galaxy.microservice.frontend.ticket.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;

public interface CustomerTicketMapper extends BaseMapper<CustomerTicket> {

    default void updateStatus(String ticketNo, int status) {
        LambdaQueryWrapper<CustomerTicket> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(CustomerTicket::getTicketNo, ticketNo);
        CustomerTicket customerTicket = this.selectOne(lambdaQueryWrapper);
        if(customerTicket == null){
            return;
        }
        customerTicket.setTccStatus(status);
        this.updateById(customerTicket);
    }
}
