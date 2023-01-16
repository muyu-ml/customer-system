package com.lcl.galaxy.cs.service.impl;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.infrastructure.exception.BizException;
import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.mapper.MybatisCustomerStaffMapper;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStaffServiceImpl implements ICustomerStaffService {

    @Autowired
    private MybatisCustomerStaffMapper customerStaffMapper;

    @Override
    public PageObject<CustomerStaff> findCustomerStaffs(Long pageSize, Long pageIndex) {
        return null;
    }

    @Override
    public List<CustomerStaff> findCustomerStaffs() {
        return null;
    }

    @Override
    public PageObject<CustomerStaff> findCustomerStaffsByName(String staffName, Long pageSize, Long pageIndex) {
        return null;
    }

    @Override
    public CustomerStaff findCustomerStaffById(Long staffId) {
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setStaffName("zhangsan");
        customerStaff.setAccountId(1234L);
        try {
            Thread.sleep(1*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1/0;
        CustomerStaff customerStaff1 = null;
        customerStaff1.setStaffName("");
        return customerStaff;
    }

    @Override
    public Boolean createCustomerStaff(CustomerStaff customerStaff) throws BizException {
        return null;
    }

    @Override
    public Boolean updateCustomerStaff(CustomerStaff customerStaff) {
        return null;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long staffId) {
        return null;
    }



}
