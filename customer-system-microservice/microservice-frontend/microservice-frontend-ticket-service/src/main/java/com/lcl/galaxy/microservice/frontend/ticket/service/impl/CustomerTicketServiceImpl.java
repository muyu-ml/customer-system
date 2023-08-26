package com.lcl.galaxy.microservice.frontend.ticket.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.frontend.ticket.event.TicketGeneratedEvent;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.TxRecordMapper;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.id.DistributedId;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.converter.CustomerTicketConverter;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;
import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.CustomerTicketMapper;
import com.lcl.galaxy.microservice.frontend.ticket.service.ICustomerTicketService;
import com.lcl.galaxy.microservice.frontend.ticket.service.ILocalCustomerStaffService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 客服工单表 服务实现类
 * </p>
 */
@Service
public class CustomerTicketServiceImpl extends ServiceImpl<CustomerTicketMapper, CustomerTicket> implements ICustomerTicketService {

    @Autowired
    CustomerTicketMapper customerTicketMapper;

    @Autowired
    ILocalCustomerStaffService localCustomerStaffService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private TxRecordMapper txRecordMapper;

    @Override
    @Transactional
    public void insertTicket(AddTicketReqVO addTicketReqVO) throws BizException {

        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(addTicketReqVO);
        customerTicket.setTicketNo(DistributedId.getInstance().getFastSimpleUUID());

//        Long staffId = addTicketReqVO.getStaffId();
//
//        //验证输入参数StaffId的正确性
//        if(Objects.isNull(staffId)) {
//            throw new BizException(MessageCode.CHECK_ERROR, "客服编号不能为空");
//        }
//
//        LocalCustomerStaff localCustomerStaff = localCustomerStaffService.findLocalCustomerStaffByStaffId(staffId);
//        if(Objects.isNull(localCustomerStaff)) {
//            throw new BizException(MessageCode.CHECK_ERROR, String.format("客服编号为%s的客服人员不存在", staffId));
//        }

        customerTicketMapper.insert(customerTicket);
    }

    @Override
    public void generateTicket(AddTicketReqVO addTicketReqVO) throws BizException {
        TicketGeneratedEvent ticketGeneratedEvent = createTicketGeneratedEvent(addTicketReqVO);
        String messageJson = JSON.toJSONString(ticketGeneratedEvent);
        Message<String> message = MessageBuilder.withPayload(messageJson).build();
        rocketMQTemplate.sendMessageInTransaction("product_group_ticket", "topic_ticket", message, null);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doGenerateTicket(TicketGeneratedEvent ticketGeneratedEvent) {
        // 幂等操作
        if(Objects.nonNull(txRecordMapper.findTxRecord(ticketGeneratedEvent.getTxNo()))){
            return;
        }

        // 插入工单
        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(ticketGeneratedEvent);
        customerTicket.setStatus(1);
        save(customerTicket);

        txRecordMapper.addTxRecord(ticketGeneratedEvent.getTxNo());
    }


    private TicketGeneratedEvent createTicketGeneratedEvent(AddTicketReqVO addTicketReqVO) {
        TicketGeneratedEvent ticketGeneratedEvent = new TicketGeneratedEvent();
        String txNo = "TX-" + DistributedId.getInstance().getFastSimpleUUID();
        ticketGeneratedEvent.setTxNo(txNo);
        String ticketNo = "TICKET-" + DistributedId.getInstance().getFastSimpleUUID();
        ticketGeneratedEvent.setTicketNo(ticketNo);
        ticketGeneratedEvent.setStaffId(addTicketReqVO.getStaffId());
        ticketGeneratedEvent.setInquire(addTicketReqVO.getInquire());
        ticketGeneratedEvent.setUserId(addTicketReqVO.getUserId());
        return ticketGeneratedEvent;
    }
}
