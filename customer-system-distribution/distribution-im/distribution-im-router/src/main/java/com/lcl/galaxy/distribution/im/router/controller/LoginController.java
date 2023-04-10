package com.lcl.galaxy.distribution.im.router.controller;

import com.lcl.galaxy.distribution.im.common.dto.IMLoginRequest;
import com.lcl.galaxy.distribution.im.common.dto.IMLoginResponse;
import com.lcl.galaxy.distribution.im.router.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public IMLoginResponse login(@RequestBody IMLoginRequest request){
        IMLoginResponse response = new IMLoginResponse();
        if(loginService.hasLogin(request.getUserid())){
            response.setCode("2001");
            response.setMsg("重复登录");
            return response;
        }
        loginService.login(request);
        response.setCode("0000");
        response.setMsg("登录成功");
        log.info("用户: {} 登录成功", request.getUserid());
        return response;
    }
}
