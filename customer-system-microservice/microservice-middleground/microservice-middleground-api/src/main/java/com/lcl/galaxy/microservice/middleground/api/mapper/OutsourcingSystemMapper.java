package com.lcl.galaxy.microservice.middleground.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.galaxy.microservice.middleground.api.entity.tenant.OutsourcingSystem;

public interface OutsourcingSystemMapper extends BaseMapper<OutsourcingSystem> {

    default IPage findPagedOutsourcingSystems(Long pageSize, Long pageIndex){
        IPage<OutsourcingSystem> page = selectPage(new Page<>(pageIndex, pageSize), null);
        return page;
    }
}
