package com.lcl.galaxy.distribution.im.server.event;

import com.lcl.galaxy.cs.infrastructure.event.DomainEvent;
import com.lcl.galaxy.distribution.im.server.entity.ImMessage;
import lombok.Data;

@Data
public class ImMessageCreatedEvent extends DomainEvent<ImMessage> {
}
