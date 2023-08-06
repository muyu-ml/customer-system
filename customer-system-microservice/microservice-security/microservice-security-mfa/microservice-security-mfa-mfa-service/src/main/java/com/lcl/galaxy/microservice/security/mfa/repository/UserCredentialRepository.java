package com.lcl.galaxy.microservice.security.mfa.repository;

import com.lcl.galaxy.microservice.security.mfa.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    UserCredential findUserCredentialByUsername(String username);
}
