package com.lcl.galaxy.microservice.frontend.ticket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.frontend.ticket.entity.Transaction;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.TransactionMapper;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.id.DistributedId;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.converter.CustomerTicketConverter;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.CustomerTicketMapper;
import com.lcl.galaxy.microservice.frontend.ticket.service.ICustomerTicketService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.TccRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TransactionMapper transactionMapper;

    @Override
    public void tickeTry(TccRequest<AddTicketReqVO> addTicketReqVOReq) {
        // 防止悬挂
        Transaction transaction = transactionMapper.load(addTicketReqVOReq.getXid(), addTicketReqVOReq.getBranchId());
        if(transaction != null){
            throw new BizException(MessageCode.CHECK_ERROR, "事务已提交");
        }
        // 插入事务记录
        transaction = new Transaction();
        transaction.setXid(addTicketReqVOReq.getXid());
        transaction.setBranchId(addTicketReqVOReq.getBranchId());
        transaction.setData(JSON.toJSONString(addTicketReqVOReq.getData()));
        transaction.setState(1);
        transactionMapper.insert(transaction);
        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(addTicketReqVOReq.getData());
        customerTicket.setTicketNo(addTicketReqVOReq.getData().getTicketNo());
        customerTicket.setTccStatus(0);
        customerTicketMapper.insert(customerTicket);
    }

    @Override
    public void ticketConfirm(TccRequest<String> ticketNoReq) {
        transactionMapper.updateBranchTransactionToCommitted(ticketNoReq.getXid(), ticketNoReq.getBranchId());
        customerTicketMapper.updateStatus(ticketNoReq.getData(), 1);
    }

    @Override
    public void ticketCencel(TccRequest<String> ticketNoReq) {
        // 空回滚：允许空回滚
        Transaction transaction = transactionMapper.load(ticketNoReq.getXid(), ticketNoReq.getBranchId());
        if(transaction == null ){
            transaction = new Transaction();
            transaction.setXid(ticketNoReq.getXid());
            transaction.setBranchId(ticketNoReq.getBranchId());
            transaction.setData(JSON.toJSONString(ticketNoReq));
            transaction.setState(3);
            transactionMapper.insert(transaction);
            return;
        }
        transactionMapper.updateBranchTransactionToRollbacked(ticketNoReq.getXid(), ticketNoReq.getBranchId());
        customerTicketMapper.updateStatus(ticketNoReq.getData(), 2);
    }
}
