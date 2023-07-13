package com.lcl.galaxy.microservice.middleground.im.router.cache;

import com.lcl.galaxy.microservice.middleground.im.common.dto.IMLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class LoginRedisRepositoryImpl implements LoginRedisRepository{

    @Autowired
    private RedisTemplate<String, IMLoginRequest> redisTemplate;

    private static final String KEY_PREFIX = "ImLogin:";

    @Override
    public void saveLogin(IMLoginRequest imLoginRequest) {
        redisTemplate.opsForValue().set(KEY_PREFIX + imLoginRequest.getUserid(), imLoginRequest);
    }

    @Override
    public void updateLogin(IMLoginRequest imLoginRequest) {
        redisTemplate.opsForValue().set(KEY_PREFIX + imLoginRequest.getUserid(), imLoginRequest);
    }

    @Override
    public void deleteLogin(String userId) {
        redisTemplate.delete(KEY_PREFIX + userId);
    }

    @Override
    public IMLoginRequest findLoginByUserId(String userId) {
        return redisTemplate.opsForValue().get(KEY_PREFIX + userId);
    }

    @Override
    public Boolean isLogin(String userId) {
        return redisTemplate.hasKey(KEY_PREFIX + userId);
    }

    @Override
    public List<IMLoginRequest> getAllIMLoginRequest() {
        Set<String> set = redisTemplate.keys(KEY_PREFIX + "*");
        List<IMLoginRequest> imLoginRequests = redisTemplate.opsForValue().multiGet(set);
        return imLoginRequests;
    }
}
