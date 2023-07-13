package com.lcl.galaxy.microservice.middleground.customer.controller;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.page.PageObject;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.middleground.customer.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.microservice.middleground.customer.controller.vo.resp.OutsourcingSystemRespVO;
import com.lcl.galaxy.microservice.middleground.customer.converter.OutsourcingSystemConverter;
import com.lcl.galaxy.microservice.middleground.customer.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.microservice.middleground.customer.service.IOutsourcingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outsourcingSystems")
public class OutsourcingSystemController {

    @Autowired
    IOutsourcingSystemService outsourcingSystemService;

    @PostMapping("/")
    public Result<Long> addOutsourcingSystem(@RequestBody AddOutsourcingSystemReqVO addOutsourcingSystemReqVO) {

        //数据转换
        OutsourcingSystem outsourcingSystem = OutsourcingSystemConverter.INSTANCE.convertCreateReq(addOutsourcingSystemReqVO);

        //调用Service层完成操作
        outsourcingSystemService.addOutsourcingSystem(outsourcingSystem);

        return Result.success(outsourcingSystem.getId());
    }

    @GetMapping("/{systemId}")
    public Result<OutsourcingSystemRespVO> findOutsourcingSystemById(@PathVariable("systemId") Long systemId) {

        OutsourcingSystem outsourcingSystem = outsourcingSystemService.findOutsourcingSystemById(systemId);

        OutsourcingSystemRespVO outsourcingSystemRespVO = OutsourcingSystemConverter.INSTANCE.convertResp(outsourcingSystem);

        return Result.success(outsourcingSystemRespVO);
    }

    @GetMapping("/page/{pageSize}/{pageIndex}")
    public Result<PageObject<OutsourcingSystemRespVO>> findOutsourcingSystems(@PathVariable("pageSize") Long pageSize, @PathVariable("pageIndex") Long pageIndex) {

        PageObject<OutsourcingSystem> pagedOutsourcingSystem = outsourcingSystemService.findPagedOutsourcingSystems(pageSize, pageIndex);

        List<OutsourcingSystemRespVO> outsourcingSystemRespVOs = OutsourcingSystemConverter.INSTANCE.convertListResp(pagedOutsourcingSystem.getList());

        PageObject<OutsourcingSystemRespVO> result = new PageObject<OutsourcingSystemRespVO>()
                .setList(outsourcingSystemRespVOs)
                .setTotal(pagedOutsourcingSystem.getTotal())
                .setPageIndex(pagedOutsourcingSystem.getPageIndex())
                .setPageSize(pagedOutsourcingSystem.getPageSize());

        return Result.success(result);
    }

    @DeleteMapping("/{systemId}")
    public Result<Boolean> deleteOutsourcingSystemById(@PathVariable("systemId") Long systemId) {

        Boolean isDeleted = outsourcingSystemService.deleteOutsourcingSystemById(systemId);

        return Result.success(isDeleted);
    }
}
