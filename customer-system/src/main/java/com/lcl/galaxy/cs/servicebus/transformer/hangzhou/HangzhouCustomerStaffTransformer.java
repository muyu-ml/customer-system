package com.lcl.galaxy.cs.servicebus.transformer.hangzhou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.entity.staff.enums.Gender;
import com.lcl.galaxy.cs.entity.staff.enums.Status;
import com.lcl.galaxy.cs.servicebus.router.hangzhou.HangzhouCustomerStaff;
import com.lcl.galaxy.cs.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class HangzhouCustomerStaffTransformer implements CustomerStaffTransformer<HangzhouCustomerStaff> {

    @Override
    public List<CustomerStaff> transformerCustomerStaff(List<HangzhouCustomerStaff> hangzhouCustomerStaffList) {
        List<CustomerStaff> customerStaffList = new ArrayList<>();
        // 把LinkedHashMap转换为List<HangzhouCustomerStaff>
        List<HangzhouCustomerStaff> hangzhouCustomerStaffs = JSON.parseArray(JSON.toJSONString(hangzhouCustomerStaffList), HangzhouCustomerStaff.class);
        for (HangzhouCustomerStaff hangzhouCustomerStaff : hangzhouCustomerStaffs) {
            CustomerStaff customerStaff = new CustomerStaff();
            BeanUtils.copyProperties(hangzhouCustomerStaff, customerStaff);
            //填充StaffName
            customerStaff.setStaffName(hangzhouCustomerStaff.getNickname());
            //转换性别
            if(hangzhouCustomerStaff.getGender() != null) {
                customerStaff.setGender(Gender.valueOf(hangzhouCustomerStaff.getGender()));
            }

            //转换时间
            if(hangzhouCustomerStaff.getCreateTime() != null) {
                ZoneId zone = ZoneId.systemDefault();
                Instant createdTimeInstance = hangzhouCustomerStaff.getCreateTime().toInstant();
                LocalDateTime createdTimeLocalDateTime = LocalDateTime.ofInstant(createdTimeInstance, zone);
                customerStaff.setCreateTime(createdTimeLocalDateTime);
            }

            //初始化AccountId和Status
            customerStaff.setAccountId(-1L);
            customerStaff.setStatus(Status.OFFLINE);

            customerStaffList.add(customerStaff);
        }
        return customerStaffList;
    }
}
