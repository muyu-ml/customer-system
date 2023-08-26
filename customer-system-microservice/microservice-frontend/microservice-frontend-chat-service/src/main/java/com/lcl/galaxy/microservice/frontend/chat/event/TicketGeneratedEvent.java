package com.lcl.galaxy.microservice.frontend.chat.event;

import lombok.Data;

@Data
public class TicketGeneratedEvent {

    private String ticketNo;

    private Long userId;

    private Long staffId;

    private String inquire;

    private String txNo;
}
