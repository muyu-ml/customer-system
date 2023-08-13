package com.lcl.galaxy.microservice.frontend.chat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;

/**
 * <p>
 * 聊天记录主表 Mapper 接口
 * </p>
 */
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

    default void updateChatSuccessStatus(String ticketNo, int status) {
        LambdaQueryWrapper<ChatRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatRecord::getTicketNo, ticketNo);
        ChatRecord chatRecord = selectOne(queryWrapper);
        chatRecord.setTccStatus(status);
        this.updateById(chatRecord);
    }
}
