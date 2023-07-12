package com.lcl.galaxy.microservice.im.server.service.impl;

import com.lcl.galaxy.microservice.im.server.entity.ImMessage;
import com.lcl.galaxy.microservice.im.server.event.ImMessageCreatedEvent;
import com.lcl.galaxy.microservice.im.server.service.ImMessageService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("event")
public class MQImMessageServiceImpl implements ImMessageService {

    private final String TOPIC_IM = "topic_im";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void saveImMessage(ImMessage imMessage) {
        ImMessageCreatedEvent event = new ImMessageCreatedEvent();
        event.setMessage(imMessage);
        event.setOperation("CREATE");
        event.setType("IM");
        rocketMQTemplate.convertAndSend(TOPIC_IM, event);
    }
}
