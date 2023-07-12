package com.lcl.galaxy.microservice.message.service.common.service;

import com.lcl.galaxy.microservice.message.service.common.entity.ImMessage;

import java.sql.SQLException;
import java.util.List;

public interface ImMessageService {

    void saveImMessage(ImMessage imMessage) throws SQLException;

    List<ImMessage> findImMessages() throws SQLException ;

    List<ImMessage> findImMessagesByUserId(Long toUserId) throws SQLException ;
}
