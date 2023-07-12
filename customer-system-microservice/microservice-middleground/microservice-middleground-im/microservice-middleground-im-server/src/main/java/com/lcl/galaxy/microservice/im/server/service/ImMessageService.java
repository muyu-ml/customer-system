package com.lcl.galaxy.microservice.im.server.service;

import com.lcl.galaxy.microservice.im.server.entity.ImMessage;

public interface ImMessageService {
    void saveImMessage(ImMessage imMessage);
}
