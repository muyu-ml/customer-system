package com.lcl.galaxy.customer.service.provider.controller.graphql.datafetcher;

import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import com.lcl.galaxy.customer.service.provider.mapper.CustomerStaffMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffDataFetcher implements DataFetcher<CustomerStaff> {
    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Override
    public CustomerStaff get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String id = String.valueOf(dataFetchingEnvironment.getArguments().get("id"));
        return customerStaffMapper.selectById(Long.valueOf(id));
    }
}
