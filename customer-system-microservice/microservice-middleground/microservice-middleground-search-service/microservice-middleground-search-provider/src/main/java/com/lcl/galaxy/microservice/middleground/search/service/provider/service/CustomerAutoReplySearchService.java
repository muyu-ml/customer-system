package com.lcl.galaxy.microservice.middleground.search.service.provider.service;

import com.lcl.galaxy.microservice.middleground.search.service.provider.controller.vo.SearchParamReq;
import com.lcl.galaxy.microservice.middleground.search.service.provider.entity.CustomerAutoReply;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.page.PageObject;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;

import java.io.IOException;

public interface CustomerAutoReplySearchService {
    Result<PageObject<CustomerAutoReply>> searchCustomerAutoReplies(SearchParamReq searchParamReq) throws IOException;
}
