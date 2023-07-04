package com.lcl.galaxy.distribution.im.router.cache;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LoginRedisRepository {
    void saveLogin(IMLoginRequest imLoginRequest);

    void updateLogin(IMLoginRequest imLoginRequest);

    void deleteLogin(String userId);

    IMLoginRequest findLoginByUserId(String userId);

    Boolean isLogin(String userId);

    List<IMLoginRequest> getAllIMLoginRequest();
}
