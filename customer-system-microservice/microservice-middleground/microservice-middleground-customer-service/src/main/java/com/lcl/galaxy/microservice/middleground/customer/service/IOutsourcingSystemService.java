package com.lcl.galaxy.microservice.middleground.customer.service;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.page.PageObject;
import com.lcl.galaxy.microservice.middleground.customer.entity.tenant.OutsourcingSystem;

import java.util.List;

public interface IOutsourcingSystemService {

    List<OutsourcingSystem> findAllOutsourcingSystems();

    PageObject<OutsourcingSystem> findPagedOutsourcingSystems(Long pageSize, Long pageIndex);

    OutsourcingSystem findOutsourcingSystemById(Long systemId);

    Boolean addOutsourcingSystem(OutsourcingSystem outsourcingSystem);

    Boolean updateOutsourcingSystem(OutsourcingSystem outsourcingSystem);

    Boolean deleteOutsourcingSystemById(Long systemId);
}
