package com.lcl.galaxy.microservice.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @GetMapping("/")
    public String getChat(){
        return "OK~";
    }
}
