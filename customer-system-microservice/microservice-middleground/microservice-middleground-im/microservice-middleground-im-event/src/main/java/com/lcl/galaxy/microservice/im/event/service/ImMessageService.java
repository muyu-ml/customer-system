package com.lcl.galaxy.microservice.im.event.service;

import com.lcl.galaxy.microservice.im.event.entity.ImMessage;

public interface ImMessageService {
    void saveImMessage(ImMessage imMessage);
}
