package com.lcl.galaxy.microservice.middleground.im.event.event;

import com.lcl.galaxy.microservice.middleground.im.event.entity.ImMessage;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.event.DomainEvent;
import lombok.Data;

@Data
public class ImMessageCreatedEvent extends DomainEvent<ImMessage> {
}
