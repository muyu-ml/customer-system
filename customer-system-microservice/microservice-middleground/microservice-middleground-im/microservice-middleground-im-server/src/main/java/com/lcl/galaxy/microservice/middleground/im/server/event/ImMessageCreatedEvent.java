package com.lcl.galaxy.microservice.middleground.im.server.event;

import com.lcl.galaxy.microservice.middleground.im.server.entity.ImMessage;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.event.DomainEvent;
import lombok.Data;

@Data
public class ImMessageCreatedEvent extends DomainEvent<ImMessage> {
}
