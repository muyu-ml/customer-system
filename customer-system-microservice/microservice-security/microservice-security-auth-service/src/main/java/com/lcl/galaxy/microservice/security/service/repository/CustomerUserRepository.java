package com.lcl.galaxy.microservice.security.service.repository;

import com.lcl.galaxy.microservice.security.service.domain.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findCustomerUserByUsername(String userName);
}
