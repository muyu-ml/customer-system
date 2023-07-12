package com.lcl.galaxy.microservice.middleground.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerGroup;

public interface CustomerGroupMapper extends BaseMapper<CustomerGroup> {

    default CustomerGroup findCustomerGroupByName(String groupName) {

        LambdaQueryWrapper<CustomerGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerGroup::getGroupName, groupName);

        return selectOne(queryWrapper);
    }
}
