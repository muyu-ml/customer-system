package com.lcl.galaxy.microservice.security.mfa.repository;

import com.lcl.galaxy.microservice.security.mfa.model.SecurityCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityCodeRepository extends JpaRepository<SecurityCode, Integer> {

    SecurityCode findSecurityCodeByUsername(String username);
}
