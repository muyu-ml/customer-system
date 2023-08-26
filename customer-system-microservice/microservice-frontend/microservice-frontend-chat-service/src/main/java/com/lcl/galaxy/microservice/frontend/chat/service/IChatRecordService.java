package com.lcl.galaxy.microservice.frontend.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import com.lcl.galaxy.microservice.frontend.chat.event.TicketGeneratedEvent;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;

/**
 * <p>
 * 聊天记录主表 服务类
 * </p>
 */
public interface IChatRecordService extends IService<ChatRecord> {

    void insertChat(AddChatReqVO addChatReqVO) throws BizException;

    void generateChatRecord(TicketGeneratedEvent ticketGeneratedEvent);


}
