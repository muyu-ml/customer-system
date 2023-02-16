package com.lcl.galaxy.cs.controller.graphql.datafetcher;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.mapper.CustomerStaffMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCustomerStaffDataFetcher implements DataFetcher<List<CustomerStaff>> {
    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Override
    public List<CustomerStaff> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerStaff :: getIsDeleted, false);
        return customerStaffMapper.selectList(queryWrapper);
    }
}
