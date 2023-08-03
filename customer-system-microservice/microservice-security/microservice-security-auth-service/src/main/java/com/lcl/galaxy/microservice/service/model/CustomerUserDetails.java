package com.lcl.galaxy.microservice.service.model;

import com.lcl.galaxy.microservice.service.domain.CustomerUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private CustomerUser customerUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customerUser.getAuthorities().stream()
                .map(a -> new SimpleGrantedAuthority(a.getName()))
                        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customerUser.getPassword();
    }

    @Override
    public String getUsername() {
        return customerUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public final CustomerUser getCustomerUser(){
        return customerUser;
    }
}
