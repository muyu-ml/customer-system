package com.lcl.galaxy.distribution.im.server;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.lcl.galaxy.distribution.ticket.DistributionImTicketApplication;
import com.lcl.galaxy.distribution.ticket.cache.BloomFilterThread;
import com.lcl.galaxy.distribution.ticket.cache.LocalCustomerStaffRedisRepository;
import com.lcl.galaxy.distribution.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.distribution.ticket.mapper.LocalCustomerStaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DistributionImTicketApplication.class)
@Slf4j
public class CustomerStaffBloomFilterTest {
    @Autowired
    private LocalCustomerStaffMapper mapper;
    @Autowired
    private LocalCustomerStaffRedisRepository redisRepository;

    public static BloomFilter<String> bloomFilter;

    @Test
    public void count(){
        log.info("CustomerStaff数量：{}", mapper.selectCount(null));
    }

    @PostConstruct
    public void init(){
        long start = System.currentTimeMillis();
        List<LocalCustomerStaff> staffs = mapper.selectList(null);
        if(staffs != null && staffs.size() > 0){
            bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), staffs.size());
            staffs.forEach(staff -> {
                bloomFilter.put(staff.getId().toString());
            });
        }
        log.info("初始化全部客服数据到布隆过滤的时间为：{}", System.currentTimeMillis() - start);
    }

    @Test
    public void customerStaffQueryTest() throws Exception {
        int consurrent = 1000;
        // 使用循环栅栏来实现1000个线程同时并发工作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(consurrent);

        // 生成1000个固定线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(consurrent);
        long start = System.currentTimeMillis();
        for (int i=0; i<consurrent; i++) {
            executorService.execute(new BloomFilterThread(cyclicBarrier, bloomFilter, mapper, redisRepository));
        }

        executorService.shutdown();

        // 等待线程池中的任务全部执行完成
        while (!executorService.isTerminated()){

        }

        log.info("1000个线程并发查询数据库，耗时：{}", System.currentTimeMillis() - start);
    }
}
