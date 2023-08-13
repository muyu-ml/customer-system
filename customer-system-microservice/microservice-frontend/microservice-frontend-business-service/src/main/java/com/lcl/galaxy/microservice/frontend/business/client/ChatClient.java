package com.lcl.galaxy.microservice.frontend.business.client;

import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "chat-service")
public interface ChatClient {

    @PostMapping(value = "/chatRecords/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO);
}
