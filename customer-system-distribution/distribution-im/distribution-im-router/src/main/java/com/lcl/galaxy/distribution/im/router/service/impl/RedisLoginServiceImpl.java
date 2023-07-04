package com.lcl.galaxy.distribution.im.router.service.impl;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;
import com.lcl.galaxy.distribution.im.router.cache.LoginRedisRepository;
import com.lcl.galaxy.distribution.im.router.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("redis")
@Primary
public class RedisLoginServiceImpl implements LoginService {

    @Autowired
    private LoginRedisRepository loginRedisRepository;

    @Override
    public void login(IMLoginRequest request) {
        loginRedisRepository.saveLogin(request);
    }

    @Override
    public void logout(String userId) {
        loginRedisRepository.deleteLogin(userId);
    }

    @Override
    public boolean hasLogin(String userId) {
        return loginRedisRepository.isLogin(userId);
    }

    @Override
    public IMLoginRequest getLoginInfo(String userId) {
        return loginRedisRepository.findLoginByUserId(userId);
    }

    @Override
    public Map<String, IMLoginRequest> getAllIMLoginRequests() {
        List<IMLoginRequest> allIMLoginRequests = loginRedisRepository.getAllIMLoginRequest();
        Map<String, IMLoginRequest> loginRequestMap = new HashMap<>(allIMLoginRequests.size());
        for(IMLoginRequest imLoginRequest: allIMLoginRequests){
            loginRequestMap.put(imLoginRequest.getUserid(), imLoginRequest);
        }
        return loginRequestMap;
    }

}
