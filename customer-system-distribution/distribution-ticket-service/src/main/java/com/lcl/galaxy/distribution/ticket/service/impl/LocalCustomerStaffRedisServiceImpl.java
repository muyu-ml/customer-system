package com.lcl.galaxy.distribution.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.distribution.ticket.cache.LocalCustomerStaffRedisRepository;
import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.distribution.ticket.mapper.LocalCustomerStaffMapper;
import com.lcl.galaxy.distribution.ticket.service.ILocalCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("redis")
@Primary
public class LocalCustomerStaffRedisServiceImpl extends ServiceImpl<LocalCustomerStaffMapper, LocalCustomerStaff> implements ILocalCustomerStaffService {

    @Autowired
    private LocalCustomerStaffRedisRepository localCustomerStaffRedisRepository;

    @Override
    @Transactional
    public void insertLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {
        localCustomerStaffRedisRepository.saveLocalCustomerStaff(localCustomerStaff);
    }

    @Override
    public void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {
        localCustomerStaffRedisRepository.updateLocalCustomerStaff(localCustomerStaff);
    }

    @Override
    public void deleteLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {
        localCustomerStaffRedisRepository.deleteLocalCustomerStaff(String.valueOf(localCustomerStaff.getStaffId()));
    }

    @Override
    public LocalCustomerStaff findLocalCustomerStaffByStaffId(Long staffId) {
        return localCustomerStaffRedisRepository.findLocalCustomerStaffByStaffId(String.valueOf(staffId));
    }
}
