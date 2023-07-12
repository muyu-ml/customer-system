package com.lcl.galaxy.microservice.search.service.provider.controller;

import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.search.service.provider.controller.vo.SearchParamReq;
import com.lcl.galaxy.microservice.search.service.provider.entity.CustomerAutoReply;
import com.lcl.galaxy.microservice.search.service.provider.service.CustomerAutoReplySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/customerAutoReply")
public class CustomerAutoReplySearchController {

    @Autowired
    private CustomerAutoReplySearchService customerAutoReplySearchService;

    @PostMapping("/search")
    public Result<PageObject<CustomerAutoReply>> search(@RequestBody SearchParamReq searchParamReq) throws IOException {
        Result<PageObject<CustomerAutoReply>> result = customerAutoReplySearchService.searchCustomerAutoReplies(searchParamReq);
        return result;
    }
}

