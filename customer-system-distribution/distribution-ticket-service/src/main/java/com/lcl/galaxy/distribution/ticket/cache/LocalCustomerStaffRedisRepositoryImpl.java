package com.lcl.galaxy.distribution.ticket.cache;

import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocalCustomerStaffRedisRepositoryImpl implements LocalCustomerStaffRedisRepository {

    @Autowired
    private RedisTemplate<String, LocalCustomerStaff> redisTemplate;

    private static final String HASH_NAME = "Staff:";

    @Override
    public void saveLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {
        redisTemplate.opsForValue().set(HASH_NAME + localCustomerStaff.getStaffId(), localCustomerStaff);
    }

    @Override
    public void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {
        redisTemplate.opsForValue().set(HASH_NAME + localCustomerStaff.getStaffId(), localCustomerStaff);
    }

    @Override
    public void deleteLocalCustomerStaff(String staffId) {
        redisTemplate.delete(staffId);
    }

    @Override
    public LocalCustomerStaff findLocalCustomerStaffByStaffId(String staffId) {
        return redisTemplate.opsForValue().get(HASH_NAME + staffId);
    }

    @Override
    public void saveEmptyCustomerStaff(String staffId) {
        redisTemplate.opsForValue().set(HASH_NAME + staffId, new LocalCustomerStaff());
    }
}
