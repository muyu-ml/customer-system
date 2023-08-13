package com.lcl.galaxy.microservice.frontend.business.controller;

import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.business.service.BusinessService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping(value = "/")
    Result<Boolean> insertTicket(@RequestBody AddTicketReqVO addTicketReqVO) {
        businessService.initializeCustomerAndTicket(addTicketReqVO.getUserId(), addTicketReqVO.getStaffId(), addTicketReqVO.getInquire());
        return Result.success(true);
    }
}
