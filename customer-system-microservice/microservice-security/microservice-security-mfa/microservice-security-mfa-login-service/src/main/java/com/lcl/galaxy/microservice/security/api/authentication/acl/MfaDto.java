package com.lcl.galaxy.microservice.security.api.authentication.acl;

import lombok.Data;

@Data
public class MfaDto {

    private String username;
    private String password;
    private String code;
}
