package com.lcl.galaxy.distribution.im.router.service.impl;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;
import com.lcl.galaxy.distribution.im.router.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MockLoginServiceImpl implements LoginService {

    private static Map<String, IMLoginRequest> loginMap = new ConcurrentHashMap<>();

    @Override
    public void login(IMLoginRequest request) {
        loginMap.putIfAbsent(request.getUserid(), request);
    }

    @Override
    public void logout(String userId) {
        loginMap.remove(userId);
    }

    @Override
    public boolean hasLogin(String userId) {
        return loginMap.containsKey(userId);
    }

    @Override
    public IMLoginRequest getLoginInfo(String userId) {
        return loginMap.get(userId);
    }

    @Override
    public Map<String, IMLoginRequest> getAllIMLoginRequests() {
        return loginMap;
    }
}
