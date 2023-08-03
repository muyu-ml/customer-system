package com.lcl.galaxy.microservice.service.repository;

import com.lcl.galaxy.microservice.service.domain.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findCustomerUserByUsername(String userName);
}
