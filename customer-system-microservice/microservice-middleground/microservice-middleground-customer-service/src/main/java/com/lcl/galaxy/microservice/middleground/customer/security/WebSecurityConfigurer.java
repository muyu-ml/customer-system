package com.lcl.galaxy.microservice.middleground.customer.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter  {

    /**
     * 认证
     * @param builder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 通过内存认证的方式初始化了两个用户及角色
        builder.inMemoryAuthentication().withUser("cs_user").password("{noop}password1").roles("USER").
                and()
                .withUser("cs_admin").password("{noop}password2").roles("ADMIN");
    }


    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 基础认证
        http.csrf().disable().httpBasic();
        // 表单认证
        http.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.GET, "/outsourcingSystems/*").permitAll()
                .mvcMatchers(HttpMethod.POST, "/outsourcingSystems/*").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE, "/outsourcingSystems/*").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

}
