package com.lcl.galaxy.distribution.im.router.service;

import com.lcl.galaxy.distribution.im.common.dto.ChatResponse;
import com.lcl.galaxy.distribution.im.common.dto.P2PChatRequest;

public interface ChatService {
    ChatResponse p2pChat(P2PChatRequest request);
}
