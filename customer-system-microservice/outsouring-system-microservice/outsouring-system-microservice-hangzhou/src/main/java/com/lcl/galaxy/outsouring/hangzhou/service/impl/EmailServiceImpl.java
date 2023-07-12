package com.lcl.galaxy.outsouring.hangzhou.service.impl;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.outsouring.hangzhou.event.CustomerStaffSyncEvent;
import com.lcl.galaxy.outsouring.hangzhou.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {


    @Override
    @EventListener
    @Async
    public void placecustomerStaffSyncNotice(CustomerStaffSyncEvent event) {
        // 模拟执行发送邮件操作
        log.info("发送邮件【{}】", JSON.toJSONString(event));
    }

    @EventListener
    public void anotherPlacecustomerStaffSyncNotice(CustomerStaffSyncEvent event) {
        // 模拟执行发送邮件操作
        log.info("另一个监听【{}】", JSON.toJSONString(event));
    }
}
