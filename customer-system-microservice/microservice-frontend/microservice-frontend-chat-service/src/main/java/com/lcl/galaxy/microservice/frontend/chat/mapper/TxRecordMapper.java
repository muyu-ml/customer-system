package com.lcl.galaxy.microservice.frontend.chat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.galaxy.microservice.frontend.chat.entity.TxRecord;

import java.time.LocalDateTime;

/**
 * <p>
 * 聊天记录主表 Mapper 接口
 * </p>
 */
public interface TxRecordMapper extends BaseMapper<TxRecord> {

    default TxRecord findTxRecord(String txNo) {
        LambdaQueryWrapper<TxRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TxRecord::getTxNo, txNo);
        return selectOne(lambdaQueryWrapper);
    }


    default void addTxRecord(String txNo){
        TxRecord txRecord = new TxRecord();
        txRecord.setTxNo(txNo);
        txRecord.setCreateTime(LocalDateTime.now());
        insert(txRecord);
    }
}
