package com.lcl.galaxy.microservice.middleground.im.router.controller;

import com.lcl.galaxy.microservice.middleground.im.common.dto.IMServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/serverInfo")
public class ServerInfoController {

    @GetMapping("/")
    public String getServiceInfo(){
        // 模拟服务器信息
        IMServerInfo imServerInfo = new IMServerInfo();
        imServerInfo.setHost("127.0.0.1");
        imServerInfo.setNettyPort(8886);
        imServerInfo.setHttpPort(9001);
        // 暂时先不用注册中心，模拟服务器地址
        return imServerInfo.toString();
    }
}
