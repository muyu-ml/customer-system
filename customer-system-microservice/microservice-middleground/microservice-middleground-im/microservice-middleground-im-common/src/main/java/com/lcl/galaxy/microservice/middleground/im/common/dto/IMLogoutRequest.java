package com.lcl.galaxy.microservice.middleground.im.common.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IMLogoutRequest {
    private String userid;
}
