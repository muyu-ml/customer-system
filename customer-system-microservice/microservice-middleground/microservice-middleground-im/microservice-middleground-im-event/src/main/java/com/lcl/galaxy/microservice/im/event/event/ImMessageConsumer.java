package com.lcl.galaxy.microservice.im.event.event;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.im.event.service.ImMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "consumer_group_im", topic = "topic_im")
public class ImMessageConsumer implements RocketMQListener<ImMessageCreatedEvent> {

    @Autowired
    private ImMessageService imMessageService;

    @Override
    public void onMessage(ImMessageCreatedEvent imMessageCreatedEvent) {
        log.info("Receive message:【{}】", JSON.toJSONString(imMessageCreatedEvent));
        imMessageService.saveImMessage(imMessageCreatedEvent.getMessage());
    }
}
