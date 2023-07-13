package com.lcl.galaxy.microservice.middleground.im.event.service;

import com.lcl.galaxy.microservice.middleground.im.event.entity.ImMessage;

public interface ImMessageService {
    void saveImMessage(ImMessage imMessage);
}
