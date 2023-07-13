package com.lcl.galaxy.microservice.middleground.im.router.cache;

import com.lcl.galaxy.microservice.middleground.im.common.dto.IMLoginRequest;

import java.util.List;

public interface LoginRedisRepository {
    void saveLogin(IMLoginRequest imLoginRequest);

    void updateLogin(IMLoginRequest imLoginRequest);

    void deleteLogin(String userId);

    IMLoginRequest findLoginByUserId(String userId);

    Boolean isLogin(String userId);

    List<IMLoginRequest> getAllIMLoginRequest();
}
