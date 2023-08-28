package com.lcl.galaxy.microservice.frontend.chat.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/chatRecords")
@Slf4j
public class ChatRecordController {

    @Autowired
    private IChatRecordService chatRecordService;


    @GetMapping("/")
    public Result<Boolean> getChats(){
        return Result.success(true);
    }

    @GetMapping("/{id}")
    public Result<String> getChatById(@PathVariable String id){
        return Result.success(id);
    }

    @PostMapping(value = "/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO) {
        if("123".equals(addChatReqVO.getUserId())){
            int i = 1/0;
        }
        chatRecordService.insertChat(addChatReqVO);
        return Result.success(true);

    }

    @GetMapping("/test")
    @SentinelResource(value = "testChats", blockHandler = "handlerBlock")
    public Result<Boolean> getChatsTest(){
        return Result.success(true);
    }

    public Result<Boolean> handlerBlock(BlockException e){
        log.info("被 Sentinel 限流了 。。。。。");
        return Result.success(false);
    }


    @GetMapping("/switch")
    public Result<Boolean> testSwitch(){
        return Result.success(true);
    }
}
