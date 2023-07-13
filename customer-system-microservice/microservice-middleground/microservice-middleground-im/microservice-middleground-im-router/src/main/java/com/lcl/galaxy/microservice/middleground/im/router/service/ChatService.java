package com.lcl.galaxy.microservice.middleground.im.router.service;

import com.lcl.galaxy.microservice.middleground.im.common.dto.ChatResponse;
import com.lcl.galaxy.microservice.middleground.im.common.dto.P2PChatRequest;

public interface ChatService {
    ChatResponse p2pChat(P2PChatRequest request);
}
