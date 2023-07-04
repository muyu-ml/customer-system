package com.lcl.galaxy.distribution.im.router.service;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;

import java.util.Map;

public interface LoginService {
    void login(IMLoginRequest request);

    void logout(String userId);

    boolean hasLogin(String userId);

    IMLoginRequest getLoginInfo(String userId);

    Map<String, IMLoginRequest> getAllIMLoginRequests();
}
