package com.lcl.galaxy.microservice.frontend.business.controller.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@Accessors(chain = true)
public class AddChatReqVO implements Serializable {

    private Long userId;

    private Long staffId;

    private String inquire;

    private String ticketNo;
}
