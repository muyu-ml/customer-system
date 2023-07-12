package com.lcl.galaxy.microservice.search.service.provider.service;

import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.search.service.provider.controller.vo.SearchParamReq;
import com.lcl.galaxy.microservice.search.service.provider.entity.CustomerAutoReply;

import java.io.IOException;

public interface CustomerAutoReplySearchService {
    Result<PageObject<CustomerAutoReply>> searchCustomerAutoReplies(SearchParamReq searchParamReq) throws IOException;
}
