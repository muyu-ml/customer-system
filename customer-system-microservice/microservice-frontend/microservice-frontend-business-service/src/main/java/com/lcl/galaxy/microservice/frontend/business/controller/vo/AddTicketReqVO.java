package com.lcl.galaxy.microservice.frontend.business.controller.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AddTicketReqVO implements Serializable {

    private String ticketNo;

    private Long userId;

    private Long staffId;

    private String inquire;
}
