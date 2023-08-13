package com.lcl.galaxy.microservice.frontend.business.action.impl;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.frontend.business.action.CreateChatTccAction;
import com.lcl.galaxy.microservice.frontend.business.client.ChatClient;
import com.lcl.galaxy.microservice.frontend.business.client.TccRequest;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.IdempotenceUtils;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateChatTccActionImpl implements CreateChatTccAction {

    @Autowired
    private ChatClient chatClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String addChatReqVO) throws BizException {
        AddChatReqVO chatReqVO = JSON.parseObject(addChatReqVO, AddChatReqVO.class);
        if(chatReqVO == null || chatReqVO.getTicketNo() == null || chatReqVO.getStaffId() == null){
            throw new BizException(MessageCode.CHECK_ERROR);
        }
        TccRequest<AddChatReqVO> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(chatReqVO);
        chatClient.chatTry(tccRequest);
        // 事务成功，保存标识
        IdempotenceUtils.setResult(getClass(), actionContext.getXid(), "success");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        // 幂等操作
        if(IdempotenceUtils.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }
        AddChatReqVO addChatReqVO = JSON.parseObject(actionContext.getActionContext("addChatReqVO").toString(), AddChatReqVO.class);
        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(addChatReqVO.getTicketNo());
        chatClient.chatConfirm(tccRequest);
        // 移除
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        // 幂等操作
        if(IdempotenceUtils.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }
        AddChatReqVO addChatReqVO = JSON.parseObject(actionContext.getActionContext("addChatReqVO").toString(), AddChatReqVO.class);
        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(addChatReqVO.getTicketNo());
        chatClient.chatCancel(tccRequest);
        // 移除
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());
        return true;
    }
}
