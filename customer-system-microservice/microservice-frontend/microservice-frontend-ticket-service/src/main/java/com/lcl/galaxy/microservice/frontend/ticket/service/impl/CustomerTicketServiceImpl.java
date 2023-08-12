package com.lcl.galaxy.microservice.frontend.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.BizException;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.exception.MessageCode;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.id.DistributedId;
import com.lcl.galaxy.microservice.frontend.ticket.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.frontend.ticket.converter.CustomerTicketConverter;
import com.lcl.galaxy.microservice.frontend.ticket.entity.CustomerTicket;
import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.microservice.frontend.ticket.mapper.CustomerTicketMapper;
import com.lcl.galaxy.microservice.frontend.ticket.service.ICustomerTicketService;
import com.lcl.galaxy.microservice.frontend.ticket.service.ILocalCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 客服工单表 服务实现类
 * </p>
 */
@Service
public class CustomerTicketServiceImpl extends ServiceImpl<CustomerTicketMapper, CustomerTicket> implements ICustomerTicketService {

    @Autowired
    CustomerTicketMapper customerTicketMapper;

    @Autowired
    ILocalCustomerStaffService localCustomerStaffService;

    @Override
    @Transactional
    public void insertTicket(AddTicketReqVO addTicketReqVO) throws BizException {

        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(addTicketReqVO);
        customerTicket.setTicketNo(DistributedId.getInstance().getFastSimpleUUID());

//        Long staffId = addTicketReqVO.getStaffId();
//
//        //验证输入参数StaffId的正确性
//        if(Objects.isNull(staffId)) {
//            throw new BizException(MessageCode.CHECK_ERROR, "客服编号不能为空");
//        }
//
//        LocalCustomerStaff localCustomerStaff = localCustomerStaffService.findLocalCustomerStaffByStaffId(staffId);
//        if(Objects.isNull(localCustomerStaff)) {
//            throw new BizException(MessageCode.CHECK_ERROR, String.format("客服编号为%s的客服人员不存在", staffId));
//        }

        customerTicketMapper.insert(customerTicket);
    }
}
