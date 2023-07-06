package com.lcl.galaxy.distribution.ticket.cache;

import com.google.common.hash.BloomFilter;
import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.distribution.ticket.mapper.LocalCustomerStaffMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;


@Slf4j
public class BloomFilterThread implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private BloomFilter<String> bloomFilter;
    private LocalCustomerStaffMapper mapper;
    private LocalCustomerStaffRedisRepository redisRepository;

    public BloomFilterThread(CyclicBarrier cyclicBarrier, BloomFilter<String> bloomFilter,
                             LocalCustomerStaffMapper mapper, LocalCustomerStaffRedisRepository redisRepository) {
        this.cyclicBarrier = cyclicBarrier;
        this.bloomFilter = bloomFilter;
        this.mapper = mapper;
        this.redisRepository = redisRepository;
    }

    @Override
    public void run() {
        try {
            // 等待所有线程准备就绪后一起执行
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String threadName = Thread.currentThread().getName();

        // 初始化 1000 个 StaffId 进行攻击
        Random random = new Random();
        String staffId = String.valueOf(random.nextInt(1000));
        // 先用布隆过滤器拦截
        if(!bloomFilter.mightContain(staffId)){
            log.info("{}：布隆过滤器中不存在该key，疑似非法请求！staffId为：{}", LocalDateTime.now(), staffId);
            return;
        }

        // 如果布隆过滤器没有挡住，需要去redis中查询缓存数据
        LocalCustomerStaff localCustomerStaff = redisRepository.findLocalCustomerStaffByStaffId(staffId);
        if(localCustomerStaff != null){
            log.info("{}：缓存在Redis中命中！staffId为：{}", LocalDateTime.now(), staffId);
            return;
        }

        // 如果缓存中没有数据，需要查询数据库
        localCustomerStaff = mapper.findLocalCustomerStaffByStaffId(Long.parseLong(staffId));
        if(localCustomerStaff != null){
            log.info("{}：数据在数据库中获取！staffId为：{}", LocalDateTime.now(), staffId);
            redisRepository.saveLocalCustomerStaff(localCustomerStaff);
            log.info("{}：缓存写入成功！staffId为：{}", LocalDateTime.now(), staffId);
            return;
        }

        // 如果数据库中也没有，就往缓存中写入一个空对象
        log.info("{}：数据在数据库中获取！staffId为：{}", LocalDateTime.now(), staffId);
        redisRepository.saveEmptyCustomerStaff(staffId);
    }
}
