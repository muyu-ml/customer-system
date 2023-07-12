package com.lcl.galaxy.microservice.middleground.api.controller.hateoas;

import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.middleground.api.controller.hateoas.assembler.HypermediaCustomerStaffAssembler;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.UpdateCustomerStaffReqVO;
import com.lcl.galaxy.microservice.middleground.api.converter.CustomerStaffConverter;
import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.service.ICustomerStaffService;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.AddCustomerStaffReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/hypermedia/customerStaffs")
public class HypermediaCustomerStaffController {

    @Autowired
    ICustomerStaffService customerStaffService;

    @Autowired
    HypermediaCustomerStaffAssembler assembler;

    //新增CustomerStaff
    @PostMapping("/")
    public Result<Long> addCustomerStaff(@RequestBody AddCustomerStaffReqVO addCustomerStaffReqVO) {

        //数据转换
        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertCreateReq(addCustomerStaffReqVO);

        //调用Service层完成操作
        customerStaffService.createCustomerStaff(customerStaff);

        return Result.success(customerStaff.getId());
    }

    @PutMapping("/")
    public Result<Boolean> updateCustomerStaff(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }

    @PutMapping("/status")
    public Result<Boolean> updateCustomerStaffStatus(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }


    @GetMapping("/{staffId}")
    public EntityModel<CustomerStaff> single(@PathVariable("staffId") Long staffId) {
        CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);

        // 为当前的请求创建一个Link
        Link selfLink = linkTo(methodOn(HypermediaCustomerStaffController.class).single(staffId)).withSelfRel();

        // 为CustomerStaff跟请求地址创建一个Link
        Link rootLink = linkTo(methodOn(HypermediaCustomerStaffController.class).all()).withRel(" ");

        return EntityModel.of(customerStaff, selfLink, rootLink);
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<CustomerStaff>> all() {
        List<CustomerStaff> customerStaffs = customerStaffService.findCustomerStaffs();
        return assembler.toCollectionModel(customerStaffs);
    }

}
