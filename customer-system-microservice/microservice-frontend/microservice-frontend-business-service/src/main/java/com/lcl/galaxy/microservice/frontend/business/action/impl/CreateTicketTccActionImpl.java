package com.lcl.galaxy.microservice.frontend.business.action.impl;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.frontend.business.action.CreateTicketTccAction;
import com.lcl.galaxy.microservice.frontend.business.client.TccRequest;
import com.lcl.galaxy.microservice.frontend.business.client.TicketClient;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.tcc.IdempotenceUtils;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTicketTccActionImpl implements CreateTicketTccAction {

    @Autowired
    private TicketClient ticketClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String addTicketReqVO) throws BizException {
        AddTicketReqVO ticketReqVO = JSON.parseObject(addTicketReqVO, AddTicketReqVO.class);
        if(ticketReqVO == null || ticketReqVO.getTicketNo() == null || ticketReqVO.getStaffId() == null){
            throw new BizException(MessageCode.CHECK_ERROR);
        }
        TccRequest<AddTicketReqVO> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(ticketReqVO);
        ticketClient.ticketTry(tccRequest);
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
        AddTicketReqVO ticketReqVO = JSON.parseObject(actionContext.getActionContext("addTicketReqVO").toString(), AddTicketReqVO.class);
        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(ticketReqVO.getTicketNo());
        ticketClient.ticketConfirm(tccRequest);
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
        AddTicketReqVO ticketReqVO = JSON.parseObject(actionContext.getActionContext("addTicketReqVO").toString(), AddTicketReqVO.class);
        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(ticketReqVO.getTicketNo());
        ticketClient.ticketCancel(tccRequest);
        // 移除
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());
        return true;
    }
}
