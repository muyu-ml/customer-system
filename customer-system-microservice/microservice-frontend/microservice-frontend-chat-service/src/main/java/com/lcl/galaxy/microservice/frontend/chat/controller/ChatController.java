package com.lcl.galaxy.microservice.frontend.chat.controller;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @GetMapping("/")
    public Result<Boolean> getChat(){
        return Result.success(true);
    }
}
