package com.lcl.galaxy.microservice.service.service;


import com.lcl.galaxy.microservice.service.model.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    /**
     * 认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomerUserDetails customerUserDetails = customerUserDetailsService.loadUserByUsername(userName);
        // 根据配置加密算法验证密码
        switch (customerUserDetails.getCustomerUser().getPasswordEncoderType()) {
            case BCRYPT:
                return checkPassword(customerUserDetails, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(customerUserDetails, password, sCryptPasswordEncoder);

        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


    private Authentication checkPassword(CustomerUserDetails customerUserDetails, String rowPassword, PasswordEncoder passwordEncoder){
        if (passwordEncoder.matches(rowPassword, customerUserDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(customerUserDetails.getUsername(), customerUserDetails.getPassword(), customerUserDetails.getAuthorities());
        }else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
