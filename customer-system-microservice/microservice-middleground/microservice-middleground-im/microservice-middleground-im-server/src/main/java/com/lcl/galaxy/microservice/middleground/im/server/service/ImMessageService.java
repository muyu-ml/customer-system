package com.lcl.galaxy.microservice.middleground.im.server.service;

import com.lcl.galaxy.microservice.middleground.im.server.entity.ImMessage;

public interface ImMessageService {
    void saveImMessage(ImMessage imMessage);
}
