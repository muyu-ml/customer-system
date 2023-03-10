package com.lcl.galaxy.cs.servicebus.router.hangzhou;

import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.cs.servicebus.router.OutsourcingSystemRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HangzhouOutsourcingSyetemRouter implements OutsourcingSystemRouter<HangzhouCustomerStaff> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<HangzhouCustomerStaff> fetchOutsourcingCustomerStaffs(String systemUrl) {
        // 通过 RestTemplate 发起远程调用
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result> result = restTemplate.exchange(systemUrl, HttpMethod.GET, null, Result.class);
        List<HangzhouCustomerStaff> customerStaffs = (List<HangzhouCustomerStaff>) result.getBody().getData();
        return customerStaffs;
    }
}
