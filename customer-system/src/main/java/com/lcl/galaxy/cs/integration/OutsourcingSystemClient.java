package com.lcl.galaxy.cs.integration;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.entity.staff.enums.Status;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OutsourcingSystemClient {
    @Autowired
    private RestTemplate restTemplate;

    public List<CustomerStaff> getCustomerStaffs(OutsourcingSystem outsourcingSystem){
        // 通过 RestTemplate 发起远程调用
        ResponseEntity<Result<List<CustomerStaff>>> result = restTemplate.exchange(outsourcingSystem.getSystemUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<Result<List<CustomerStaff>>>() {});

        List<CustomerStaff> customerStaffs = (List<CustomerStaff>) result.getBody().getData();

        for(CustomerStaff customerStaff : customerStaffs){
            customerStaff.setId(null);
            customerStaff.setAccountId(1L);
            customerStaff.setStatus(Status.ONLINE);
        }

        return customerStaffs;
    }
}
