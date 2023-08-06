package com.lcl.galaxy.microservice.security.mfa.service;

import com.lcl.galaxy.microservice.security.mfa.model.SecurityCode;
import com.lcl.galaxy.microservice.security.mfa.model.UserCredential;
import com.lcl.galaxy.microservice.security.mfa.repository.SecurityCodeRepository;
import com.lcl.galaxy.microservice.security.mfa.repository.UserCredentialRepository;
import com.lcl.galaxy.microservice.security.mfa.util.SecurityCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MfaService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private SecurityCodeRepository securityCodeRepository;

    public void addUserCredential(UserCredential userCredential){
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
    }

    public void validateUserCredential(UserCredential userCredentialToValidate) {
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUsername(userCredentialToValidate.getUsername());
        if(!Objects.isNull(userCredential)) {
            if(passwordEncoder.matches(userCredentialToValidate.getPassword(), userCredential.getPassword())){
                generateOrRefreshSecurityCode(userCredential);
            } else {
                throw new BadCredentialsException("用户名密码错误");
            }
        }else {
            throw new BadCredentialsException("用户名密码错误");
        }
    }

    public boolean validateSecurityCode(SecurityCode securityCodeToValidate){
        SecurityCode securityCode = securityCodeRepository.findSecurityCodeByUsername(securityCodeToValidate.getUsername());
        if(!Objects.isNull(securityCode) && securityCode.getCode().equals(securityCodeToValidate.getCode())){
            return true;
        }
        return false;
    }


    private void generateOrRefreshSecurityCode(UserCredential userCredential){
        String generateSecurityCode = SecurityCodeUtils.generateSecurityCode();
        SecurityCode securityCode = securityCodeRepository.findSecurityCodeByUsername(userCredential.getUsername());
        if(Objects.isNull(securityCode)){
            securityCode = new SecurityCode();
            securityCode.setUsername(userCredential.getUsername());
            securityCode.setCode(generateSecurityCode);
        }else {
            securityCode.setCode(generateSecurityCode);
        }
        securityCodeRepository.save(securityCode);
    }
}
