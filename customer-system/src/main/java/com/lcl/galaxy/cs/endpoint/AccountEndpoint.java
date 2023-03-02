package com.lcl.galaxy.cs.endpoint;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.mapper.CustomerStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "account", enableByDefault = true)
public class AccountEndpoint {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @ReadOperation
    public Map<String, Object> getMySystemInfo(@Selector String arg0){
        Map<String, Object> result = new HashMap<>();
        CustomerStaff customerStaff = customerStaffMapper.selectById(arg0);
        result.put("staffName", customerStaff.getStaffName());
        return result;
    }
}
