package com.lcl.galaxy.microservice.middleground.im.server.service.impl;

import com.lcl.galaxy.microservice.middleground.im.server.entity.ImMessage;
import com.lcl.galaxy.microservice.middleground.im.server.event.ImMessageCreatedEvent;
import com.lcl.galaxy.microservice.middleground.im.server.service.ImMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service("event_delay")
public class MQDelayImMessageServiceImpl implements ImMessageService {

    private final String TOPIC_IM = "topic_im_delay";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void saveImMessage(ImMessage imMessage) {
        ImMessageCreatedEvent event = new ImMessageCreatedEvent();
        event.setMessage(imMessage);
        event.setOperation("CREATE");
        event.setType("IM");
        log.info("延时消息发送时间：{}", LocalDateTime.now());
        rocketMQTemplate.syncSend(TOPIC_IM, MessageBuilder.withPayload(event).build(), 2000, 4);
    }
}
