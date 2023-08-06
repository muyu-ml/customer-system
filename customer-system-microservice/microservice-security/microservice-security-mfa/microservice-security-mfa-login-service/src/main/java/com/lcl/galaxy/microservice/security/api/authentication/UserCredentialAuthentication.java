package com.lcl.galaxy.microservice.security.api.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserCredentialAuthentication extends UsernamePasswordAuthenticationToken {

    public UserCredentialAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
