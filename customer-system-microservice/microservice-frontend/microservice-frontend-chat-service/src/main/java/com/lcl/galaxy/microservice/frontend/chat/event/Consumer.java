package com.lcl.galaxy.microservice.frontend.chat.event;


import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "product_group_ticket", topic = "topic_ticket")
public class Consumer implements RocketMQListener<String> {

    @Autowired
    private IChatRecordService chatRecordService;

    @Override
    public void onMessage(String s) {
        TicketGeneratedEvent ticketGeneratedEvent = JSON.parseObject(s, TicketGeneratedEvent.class);
        chatRecordService.generateChatRecord(ticketGeneratedEvent);
    }
}
