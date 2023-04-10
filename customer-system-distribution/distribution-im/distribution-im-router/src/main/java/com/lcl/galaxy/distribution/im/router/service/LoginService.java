package com.lcl.galaxy.distribution.im.router.service;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;

public interface LoginService {
    void login(IMLoginRequest request);

    boolean hasLogin(String userId);

    IMLoginRequest getLoginInfo(String userId);
}
