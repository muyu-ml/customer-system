package com.lcl.galaxy.outsouring.hangzhou.controller;

import com.lcl.galaxy.outsouring.hangzhou.controller.vo.req.HangzhouCustomerStaffCreateReqVO;
import com.lcl.galaxy.outsouring.hangzhou.controller.vo.req.HangzhouCustomerStaffUpdateReqVO;
import com.lcl.galaxy.outsouring.hangzhou.controller.vo.resp.HangzhouCustomerStaffRespVO;
import com.lcl.galaxy.outsouring.hangzhou.converter.HangzhouCustomerStaffConverter;
import com.lcl.galaxy.outsouring.hangzhou.entity.HangzhouCustomerStaff;
import com.lcl.galaxy.outsouring.hangzhou.service.HangzhouCustomerStaffService;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customerStaffs/hangzhou/")
public class HangzhouCustomerStaffController {

    @Autowired
    private HangzhouCustomerStaffService customerStaffService;

    @PostMapping("/")
    public Result<HangzhouCustomerStaffRespVO> addCustomerStaff(@RequestBody @Validated HangzhouCustomerStaffCreateReqVO customerStaffCreateReqVO) {
        HangzhouCustomerStaff customerStaff = HangzhouCustomerStaffConverter.INSTANCE.convertCreateReq(customerStaffCreateReqVO);

        return Result.success(customerStaffService.createCustomerStaff(customerStaff));
    }

    @PutMapping("/")
    public Result<HangzhouCustomerStaffRespVO> updateCustomerStaff(@RequestBody @Validated HangzhouCustomerStaffUpdateReqVO doctorUpdateReqVO) {
        HangzhouCustomerStaff customerStaff = HangzhouCustomerStaffConverter.INSTANCE.convertUpdateReq(doctorUpdateReqVO);

        return Result.success(customerStaffService.updateCustomerStaff(customerStaff));
    }

    @DeleteMapping("/")
    public Result<HangzhouCustomerStaffRespVO> deleteCustomerStaff(@RequestParam ("id") Long id) {
        return Result.success(customerStaffService.deleteCustomerStaffById(id));
    }

    @GetMapping("/")
    public Result<List<HangzhouCustomerStaffRespVO>> getAllCustomerStaffs() {
        List<HangzhouCustomerStaff> customerStaffs = customerStaffService.findAllCustomerStaffs();

        return Result.success(HangzhouCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }

    @GetMapping("/updated")
    public Result<HangzhouCustomerStaffRespVO> getCustomerStaffsByUpdatedTime(@RequestParam ("updatedTime") Long updatedTime) {
        Date updatedTimeForQuery = new Date(updatedTime);
        List<HangzhouCustomerStaff> customerStaffs = customerStaffService.findCustomerStaffsByUpdatedTime(updatedTimeForQuery);

        return Result.success(HangzhouCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }
}

