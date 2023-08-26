package com.lcl.galaxy.microservice.frontend.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.converter.ChatRecordConverter;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import com.lcl.galaxy.microservice.frontend.chat.event.TicketGeneratedEvent;
import com.lcl.galaxy.microservice.frontend.chat.mapper.ChatRecordMapper;
import com.lcl.galaxy.microservice.frontend.chat.mapper.TxRecordMapper;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 聊天记录主表 服务实现类
 * </p>
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {
    @Autowired
    ChatRecordMapper chatRecordMapper;
    @Autowired
    private TxRecordMapper txRecordMapper;

    @Override
    @Transactional
    public void insertChat(AddChatReqVO addChatReqVO) throws BizException {

        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(addChatReqVO);
        chatRecordMapper.insert(chatRecord);
    }

    @Override
    public void generateChatRecord(TicketGeneratedEvent ticketGeneratedEvent) {
        // 判断幂等
        if(Objects.nonNull(txRecordMapper.findTxRecord(ticketGeneratedEvent.getTicketNo()))){
            return;
        }

        // 存入业务表
        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(ticketGeneratedEvent);
        save(chatRecord);
        // 存入事务表
        txRecordMapper.addTxRecord(ticketGeneratedEvent.getTxNo());
    }
}
