package com.lcl.galaxy.microservice.frontend.ticket.event;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.TxRecordMapper;
import com.lcl.galaxy.microservice.frontend.ticket.service.ICustomerTicketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

//public class ProductListener implements TransactionListener {
//
//    @Override
//    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
//        return null;
//    }
//
//    @Override
//    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
//        return null;
//    }
//}


@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "product_group_ticket")
public class ProductListener implements RocketMQLocalTransactionListener {

    @Autowired
    private ICustomerTicketService customerTicketService;
    @Autowired
    private TxRecordMapper txRecordMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try{
            // 消息转换
            TicketGeneratedEvent ticketGeneratedEvent = converEvent(message);
            // 执行本地事务
            customerTicketService.doGenerateTicket(ticketGeneratedEvent);

            // 告诉 RocketMQ 事务提交成功
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e){
            // 本地事务执行失败，告诉 RocketMQ 事务提交失败
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // 消息转换
        TicketGeneratedEvent ticketGeneratedEvent = converEvent(message);

        // 查看事务是否提交
        boolean isTxNoExists = Objects.nonNull(txRecordMapper.findTxRecord(ticketGeneratedEvent.getTxNo()));
        // 如果事务已提交，返回 COMMIT
        if(isTxNoExists){
            return RocketMQLocalTransactionState.COMMIT;
        }
        // 如果事务没有提交，返回 ROLLBACK
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    private TicketGeneratedEvent converEvent(Message message) {
        String messageString = new String((byte[]) message.getPayload());
        TicketGeneratedEvent ticketGeneratedEvent = JSON.parseObject(messageString, TicketGeneratedEvent.class);
        return ticketGeneratedEvent;
    }
}