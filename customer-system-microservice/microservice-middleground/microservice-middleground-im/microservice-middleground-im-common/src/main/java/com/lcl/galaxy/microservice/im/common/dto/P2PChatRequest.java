package com.lcl.galaxy.microservice.im.common.dto;

import lombok.Data;

@Data
public class P2PChatRequest {
    private String fromUserId;
    private String toUserId;
    private String msg;
}
