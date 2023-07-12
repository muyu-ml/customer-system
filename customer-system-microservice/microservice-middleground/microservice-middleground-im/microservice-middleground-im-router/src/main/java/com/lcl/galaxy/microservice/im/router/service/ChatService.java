package com.lcl.galaxy.microservice.im.router.service;

import com.lcl.galaxy.microservice.im.common.dto.ChatResponse;
import com.lcl.galaxy.microservice.im.common.dto.P2PChatRequest;

public interface ChatService {
    ChatResponse p2pChat(P2PChatRequest request);
}
