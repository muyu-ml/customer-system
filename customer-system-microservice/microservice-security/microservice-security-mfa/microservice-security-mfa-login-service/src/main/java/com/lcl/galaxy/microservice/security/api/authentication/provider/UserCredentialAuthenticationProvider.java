package com.lcl.galaxy.microservice.security.api.authentication.provider;

import com.lcl.galaxy.microservice.security.api.authentication.UserCredentialAuthentication;
import com.lcl.galaxy.microservice.security.api.authentication.acl.MfaAcl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MfaAcl mfaAcl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        //调用认证服务完成认证
        mfaAcl.validateUserCredential(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserCredentialAuthentication.class.isAssignableFrom(aClass);
    }
}

