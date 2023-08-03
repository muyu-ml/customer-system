package com.lcl.galaxy.microservice.security.service.service;

import com.lcl.galaxy.microservice.security.service.model.CustomerUserDetails;
import com.lcl.galaxy.microservice.security.service.domain.CustomerUser;
import com.lcl.galaxy.microservice.security.service.repository.CustomerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerUserRepository customerUserRepository;

    @Override
    public CustomerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("username" + username + "bot found");
        CustomerUser customerUser = customerUserRepository.findCustomerUserByUsername(username)
                .orElseThrow(supplier);
        return new CustomerUserDetails(customerUser);
    }
}
