package com.lcl.galaxy.microservice.middleground.im.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.middleground.im.server.entity.ImMessage;
import com.lcl.galaxy.microservice.middleground.im.server.mapper.ImMessageMapper;
import com.lcl.galaxy.microservice.middleground.im.server.service.ImMessageService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ImMessageServiceImpl extends ServiceImpl<ImMessageMapper, ImMessage> implements ImMessageService {

    ExecutorService pool = Executors.newFixedThreadPool(2);

    @Override
    public void saveImMessage(ImMessage imMessage) {
        pool.submit(new ImMessageTask(imMessage));
    }


    class ImMessageTask implements Runnable {

        private ImMessage imMessage;

        public ImMessageTask(ImMessage imMessage){
            this.imMessage = imMessage;
        }

        @Override
        public void run() {
            save(imMessage);
        }
    }
}
