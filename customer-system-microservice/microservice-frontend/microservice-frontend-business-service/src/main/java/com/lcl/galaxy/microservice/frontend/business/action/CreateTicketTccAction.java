package com.lcl.galaxy.microservice.frontend.business.action;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface CreateTicketTccAction {

    @TwoPhaseBusinessAction(name = "CreateTicketTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "addTicketReqVO") String addTicketReqVO) throws BizException;

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
