package com.lcl.galaxy.microservice.frontend.chat.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.converter.ChatRecordConverter;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import com.lcl.galaxy.microservice.frontend.chat.entity.Transaction;
import com.lcl.galaxy.microservice.frontend.chat.mapper.ChatRecordMapper;
import com.lcl.galaxy.microservice.frontend.chat.mapper.TransactionMapper;
import com.lcl.galaxy.microservice.frontend.chat.service.IChatRecordService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.TccRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TransactionMapper transactionMapper;

    @Override
    public void insertChat(TccRequest<AddChatReqVO> addChatReqVOReq) throws BizException {
        // 防止悬挂
        Transaction transaction = transactionMapper.load(addChatReqVOReq.getXid(), addChatReqVOReq.getBranchId());
        if(transaction != null){
            throw new BizException(MessageCode.CHECK_ERROR, "事务已提交");
        }
        // 插入事务记录
        transaction = new Transaction();
        transaction.setXid(addChatReqVOReq.getXid());
        transaction.setBranchId(addChatReqVOReq.getBranchId());
        transaction.setData(JSON.toJSONString(addChatReqVOReq.getData()));
        transaction.setState(1);
        transactionMapper.insert(transaction);

        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(addChatReqVOReq.getData());
        chatRecord.setTccStatus(0);
        chatRecordMapper.insert(chatRecord);
    }

    @Override
    public void updateChatSuccessStatus(TccRequest<String> ticketNoReq) {
        transactionMapper.updateBranchTransactionToCommitted(ticketNoReq.getXid(), ticketNoReq.getBranchId());
        chatRecordMapper.updateChatSuccessStatus(ticketNoReq.getData(), 1);
    }

    @Override
    public void updateChatFailStatus(TccRequest<String> ticketNoReq) {
        // 空回滚：允许空回滚
        Transaction transaction = transactionMapper.load(ticketNoReq.getXid(), ticketNoReq.getBranchId());
        if(transaction == null ){
            transaction = new Transaction();
            transaction.setXid(ticketNoReq.getXid());
            transaction.setBranchId(ticketNoReq.getBranchId());
            transaction.setData(JSON.toJSONString(ticketNoReq));
            transaction.setState(3);
            transactionMapper.insert(transaction);
            return;
        }
        chatRecordMapper.updateChatSuccessStatus(ticketNoReq.getData(), 2);
    }
}
