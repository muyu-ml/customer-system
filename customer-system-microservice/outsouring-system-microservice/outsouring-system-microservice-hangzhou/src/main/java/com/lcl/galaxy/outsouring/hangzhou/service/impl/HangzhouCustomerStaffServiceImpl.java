package com.lcl.galaxy.outsouring.hangzhou.service.impl;

import com.lcl.galaxy.cs.infrastructure.exception.BizException;
import com.lcl.galaxy.cs.infrastructure.exception.MessageCode;
import com.lcl.galaxy.outsouring.hangzhou.entity.HangzhouCustomerStaff;
import com.lcl.galaxy.outsouring.hangzhou.event.CustomerStaffSyncEvent;
import com.lcl.galaxy.outsouring.hangzhou.repository.HangzhouCustomerStaffRepository;
import com.lcl.galaxy.outsouring.hangzhou.service.HangzhouCustomerStaffService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class HangzhouCustomerStaffServiceImpl implements HangzhouCustomerStaffService {

    @Autowired
    HangzhouCustomerStaffRepository customerStaffRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<HangzhouCustomerStaff> findAllCustomerStaffs() {
        CustomerStaffSyncEvent customerStaffSyncEvent = new CustomerStaffSyncEvent("findAllCustomerStaffs");
        publisher.publishEvent(customerStaffSyncEvent);
        return customerStaffRepository.findByIsDeletedFalse();
    }

    @Override
    public List<HangzhouCustomerStaff> findCustomerStaffsByUpdatedTime(Date updatedTime) {
        return customerStaffRepository.findByUpdatedAtAfter(updatedTime);
    }

    @Override
    public HangzhouCustomerStaff createCustomerStaff(HangzhouCustomerStaff customerStaff) {
        return customerStaffRepository.save(customerStaff);
    }

    @Override
    public HangzhouCustomerStaff updateCustomerStaff(HangzhouCustomerStaff customerStaff) {
        String lockKey = "HangzhouCustomerStaff" + customerStaff.getId();

        // 获取 Lock 对象，并判断是否为空
        RLock lock = redissonClient.getLock(lockKey);
        if(Objects.isNull(lock)){
            log.info("获取锁为空");
            throw new BizException(MessageCode.SYSTEM_ERROR, "获取锁为空");
        }
        // 尝试加锁，如果加锁失败则抛出异常
        boolean tryLock;
        try{
            tryLock = lock.tryLock(3, 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("获取锁异常");
            throw new BizException(MessageCode.SYSTEM_ERROR, "获取锁异常");
        }

        // 没有获取到锁
        if(!tryLock){
            log.info("没有获取到锁");
            throw new BizException(MessageCode.SYSTEM_ERROR, "没有获取到锁");
        }

        try {
            log.info("业务执行成功");
            return customerStaffRepository.save(customerStaff);
        }catch (Exception e){
            e.printStackTrace();
            log.info("业务执行异常");
            throw new BizException(MessageCode.SYSTEM_ERROR, "业务执行异常");
        }finally {
            // 判断要解锁的key是否已经被加锁，只有加锁成功的key才需要解锁
            // 判断要解锁的key是否被当前线程持有，自己加的锁自己才能解
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    @Override
    public HangzhouCustomerStaff deleteCustomerStaffById(Long id) {
        HangzhouCustomerStaff customerStaff = new HangzhouCustomerStaff().setId(id).setIsDeleted(true);

        return customerStaffRepository.save(customerStaff);
    }
}
