package com.lcl.galaxy.microservice.frontend.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.TccRequest;

/**
 * <p>
 * 聊天记录主表 服务类
 * </p>
 */
public interface IChatRecordService extends IService<ChatRecord> {

    void insertChat(TccRequest<AddChatReqVO> addChatReqVO) throws BizException;

    void updateChatSuccessStatus(TccRequest<String> ticketNo);

    void updateChatFailStatus(TccRequest<String> ticketNo);
}
