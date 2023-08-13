package com.lcl.galaxy.microservice.frontend.business.client;

import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "chat-service")
public interface ChatClient {

    @PostMapping(value = "/chatRecords/try")
    Result<Boolean> chatTry(@RequestBody TccRequest<AddChatReqVO> addChatReqVO);

    @PostMapping(value = "/chatRecords/confirm")
    Result<Boolean> chatConfirm(@RequestBody TccRequest<String> ticketNo);

    @PostMapping(value = "/chatRecords/cancel")
    Result<Boolean> chatCancel(@RequestBody TccRequest<String> ticketNo);
}
