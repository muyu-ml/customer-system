package com.lcl.galaxy.microservice.service.config;

import com.lcl.galaxy.microservice.service.service.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProviderService authenticationProviderService;


    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests().anyRequest().authenticated();
    }


    /**
     * 认证
     * @param builder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(authenticationProviderService);
    }


}
