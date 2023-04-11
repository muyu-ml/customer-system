package com.lcl.galaxy.message.service.common.mapper;

import com.lcl.galaxy.message.service.common.entity.ImMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ImMessageMapper{
    void addImMessage(ImMessage entity) throws SQLException;

    List<ImMessage> findImMessages() throws SQLException;

    List<ImMessage> findImMessagesByUser(@Param("toUserId") Long toUserId);
}
