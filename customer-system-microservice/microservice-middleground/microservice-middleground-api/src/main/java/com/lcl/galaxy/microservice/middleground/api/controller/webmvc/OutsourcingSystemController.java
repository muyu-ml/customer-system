package com.lcl.galaxy.microservice.middleground.api.controller.webmvc;

import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.middleground.api.converter.OutsourcingSystemConverter;
import com.lcl.galaxy.microservice.middleground.api.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.microservice.middleground.api.service.IOutsourcingSystemService;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.req.AddOutsourcingSystemReqVO;
import com.lcl.galaxy.microservice.middleground.api.controller.vo.resp.OutsourcingSystemRespVO;
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
