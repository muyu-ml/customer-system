package com.lcl.galaxy.microservice.frontend.business.action;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface CreateChatTccAction {

    @TwoPhaseBusinessAction(name = "CreateChatTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "addChatReqVO") String addChatReqVO) throws BizException;

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
