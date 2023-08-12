package com.lcl.galaxy.microservice.frontend.chat.controller;

import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/chatRecords")
public class ChatRecordController {

    @Autowired
    private IChatRecordService chatRecordService;

    @PostMapping(value = "/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO) {

        chatRecordService.insertChat(addChatReqVO);
        return Result.success(true);

    }
}
