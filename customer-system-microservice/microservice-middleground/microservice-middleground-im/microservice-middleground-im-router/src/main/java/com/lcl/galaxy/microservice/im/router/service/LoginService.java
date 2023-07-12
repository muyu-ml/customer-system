package com.lcl.galaxy.microservice.im.router.service;

import com.lcl.galaxy.microservice.im.common.dto.IMLoginRequest;

import java.util.Map;

public interface LoginService {
    void login(IMLoginRequest request);

    void logout(String userId);

    boolean hasLogin(String userId);

    IMLoginRequest getLoginInfo(String userId);

    Map<String, IMLoginRequest> getAllIMLoginRequests();
}
