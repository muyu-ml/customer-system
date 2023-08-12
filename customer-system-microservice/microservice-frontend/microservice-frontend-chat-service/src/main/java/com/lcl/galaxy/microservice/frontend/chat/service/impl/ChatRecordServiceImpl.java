package com.lcl.galaxy.microservice.frontend.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.converter.ChatRecordConverter;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import com.lcl.galaxy.microservice.frontend.chat.mapper.ChatRecordMapper;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 聊天记录主表 服务实现类
 * </p>
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {
    @Autowired
    ChatRecordMapper chatRecordMapper;

    @Override
    @Transactional
    public void insertChat(AddChatReqVO addChatReqVO) throws BizException {

        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(addChatReqVO);
        chatRecordMapper.insert(chatRecord);
    }
}
