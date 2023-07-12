package com.lcl.galaxy.microservice.im.router.service.impl;

import com.lcl.galaxy.microservice.im.common.dto.ChatResponse;
import com.lcl.galaxy.microservice.im.common.dto.IMLoginRequest;
import com.lcl.galaxy.microservice.im.common.dto.P2PChatRequest;
import com.lcl.galaxy.microservice.im.router.service.ChatService;
import com.lcl.galaxy.microservice.im.router.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ChatResponse p2pChat(P2PChatRequest request) {

        // 先通过登录信息获取模板服务器地址
        IMLoginRequest loginRequest = loginService.getLoginInfo(request.getToUserId());
        // 向目标服务器地址发起聊天请求
        ChatResponse response = restTemplate.postForObject("http://" + loginRequest.getServerHost() + ":" + loginRequest.getHttpPort() + "/p2p/chat", request, ChatResponse.class);
        return response;
    }
}
