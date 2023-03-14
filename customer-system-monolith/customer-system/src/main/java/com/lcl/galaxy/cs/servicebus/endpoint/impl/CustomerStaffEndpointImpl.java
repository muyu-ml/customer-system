package com.lcl.galaxy.cs.servicebus.endpoint.impl;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.cs.servicebus.endpoint.CustomerStaffEndpoint;
import com.lcl.galaxy.cs.servicebus.filter.CustomerStaffFilterChain;
import com.lcl.galaxy.cs.servicebus.filter.impl.CustomerStaffEmptyFilter;
import com.lcl.galaxy.cs.servicebus.router.OutsourcingSystemRouter;
import com.lcl.galaxy.cs.servicebus.router.OutsourcingSystemRouterFactory;
import com.lcl.galaxy.cs.servicebus.transformer.CustomerStaffTransformer;
import com.lcl.galaxy.cs.servicebus.transformer.CustomerStaffTransformerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerStaffEndpointImpl implements CustomerStaffEndpoint {

    private CustomerStaffFilterChain customerStaffFilterChain;

    /**
     * 在构造函数中创建过滤链
     */
    public CustomerStaffEndpointImpl(){
        customerStaffFilterChain = new CustomerStaffFilterChain();
        customerStaffFilterChain.addFilter(new CustomerStaffEmptyFilter());
    }

    @Override
    public List<CustomerStaff> fetchCustomerStaffs(OutsourcingSystem outsourcingSystem) {
        // 定义最终返回的结果集
        List<CustomerStaff> finalCustomerStaffs = new ArrayList<>();
        // 获取路由器
        OutsourcingSystemRouter outsourcingSystemRouter = OutsourcingSystemRouterFactory.createRouter(outsourcingSystem.getSystemCode());
        // 使用路由器进行远程调用，获取原始数据
        List list = outsourcingSystemRouter.fetchOutsourcingCustomerStaffs(outsourcingSystem.getSystemUrl());
        // 创建数据转换器
        CustomerStaffTransformer customerStaffTransformer = CustomerStaffTransformerFactory.createTransformer(outsourcingSystem.getSystemCode());
        // 使用转换器转换数据
        List<CustomerStaff> customerStaffs = customerStaffTransformer.transformerCustomerStaff(list);
        // 执行过滤链
        customerStaffs.forEach(customerStaff -> {
            CustomerStaff filterCustomerStaff = customerStaffFilterChain.execute(customerStaff);
            if(filterCustomerStaff != null) {
                finalCustomerStaffs.add(filterCustomerStaff);
            }
        });
        return finalCustomerStaffs;
    }
}
