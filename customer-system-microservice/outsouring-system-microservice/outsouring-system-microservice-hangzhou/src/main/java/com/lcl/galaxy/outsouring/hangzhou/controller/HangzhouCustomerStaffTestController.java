package com.lcl.galaxy.outsouring.hangzhou.controller;

import com.lcl.galaxy.outsouring.hangzhou.entity.HangzhouCustomerStaff;
import com.lcl.galaxy.outsouring.hangzhou.service.HangzhouCustomerStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/customerStaffs/hangzhou/test")
public class HangzhouCustomerStaffTestController {

    @Autowired
    private HangzhouCustomerStaffService customerStaffService;

    @PostMapping("/")
    public void test() {
        testCustomerStaffUpdate();
    }


    void testCustomerStaffUpdate(){
        int corePoolSize = 16;
        int maxPoolSize = 50;
        long keepLivedTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue queue = new LinkedBlockingQueue(1000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepLivedTime, unit, queue);
        for (int i=0; i<1000; i++) {
            executor.execute(new UpdateCustomerStaffTask(buildCustomerStaff()));
        }
    }


    class UpdateCustomerStaffTask extends Thread {
        private HangzhouCustomerStaff hangzhouCustomerStaff;

        public UpdateCustomerStaffTask(HangzhouCustomerStaff hangzhouCustomerStaff){
            this.hangzhouCustomerStaff = hangzhouCustomerStaff;
        }

        @Override
        public void run(){
            customerStaffService.updateCustomerStaff(hangzhouCustomerStaff);
        }
    }

    public HangzhouCustomerStaff buildCustomerStaff(){
        HangzhouCustomerStaff hangzhouCustomerStaff = new HangzhouCustomerStaff();
        hangzhouCustomerStaff.setId(1L);
        hangzhouCustomerStaff.setAvatar("abc.jpg");
        hangzhouCustomerStaff.setNickname("lcl");
        hangzhouCustomerStaff.setGender("MALE");
        hangzhouCustomerStaff.setGoodAt("擅长X");
        hangzhouCustomerStaff.setRemark("remark");
        hangzhouCustomerStaff.setCreatedAt(new Date());
        hangzhouCustomerStaff.setUpdatedAt(new Date());
        hangzhouCustomerStaff.setIsDeleted(false);
        return hangzhouCustomerStaff;
    }
}

