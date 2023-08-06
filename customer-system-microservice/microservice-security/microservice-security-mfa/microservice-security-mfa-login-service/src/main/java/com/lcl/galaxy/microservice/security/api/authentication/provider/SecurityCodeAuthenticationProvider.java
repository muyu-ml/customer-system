package com.lcl.galaxy.microservice.security.api.authentication.provider;

import com.lcl.galaxy.microservice.security.api.authentication.SecurityCodeAuthentication;
import com.lcl.galaxy.microservice.security.api.authentication.acl.MfaAcl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SecurityCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MfaAcl mfaAcl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        //调用认证服务完成认证
        boolean result = mfaAcl.validateSecurityCode(username, code);

        if (result) {
            return new SecurityCodeAuthentication(username, code);
        } else {
            throw new BadCredentialsException("安全码认证失败");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SecurityCodeAuthentication.class.isAssignableFrom(aClass);
    }
}
