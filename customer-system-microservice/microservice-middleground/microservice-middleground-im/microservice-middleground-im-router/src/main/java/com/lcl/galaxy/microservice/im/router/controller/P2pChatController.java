package com.lcl.galaxy.microservice.im.router.controller;

import com.lcl.galaxy.microservice.im.common.dto.ChatResponse;
import com.lcl.galaxy.microservice.im.common.dto.P2PChatRequest;
import com.lcl.galaxy.microservice.im.router.service.ChatService;
import com.lcl.galaxy.microservice.im.router.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/p2p")
public class P2pChatController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request){
        ChatResponse response = new ChatResponse();
        if(!loginService.hasLogin(request.getFromUserId())){
            response.setCode("3001");
            response.setMsg("请先登录");
            return response;
        }

        if(!loginService.hasLogin(request.getToUserId())){
            response.setCode("3002");
            response.setMsg("对方请先登录");
            return response;
        }
        response = chatService.p2pChat(request);

        return response;
    }
}
