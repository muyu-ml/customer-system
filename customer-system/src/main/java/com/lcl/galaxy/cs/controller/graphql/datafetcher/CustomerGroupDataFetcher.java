package com.lcl.galaxy.cs.controller.graphql.datafetcher;

import com.lcl.galaxy.cs.entity.staff.CustomerGroup;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.mapper.CustomerGroupMapper;
import com.lcl.galaxy.cs.mapper.CustomerStaffMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerGroupDataFetcher implements DataFetcher<CustomerGroup> {
    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Override
    public CustomerGroup get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        CustomerStaff customerStaff = dataFetchingEnvironment.getSource();
        if(customerStaff != null){
            return customerGroupMapper.selectById(customerStaff.getGroupId());
        }
        return null;
    }
}
