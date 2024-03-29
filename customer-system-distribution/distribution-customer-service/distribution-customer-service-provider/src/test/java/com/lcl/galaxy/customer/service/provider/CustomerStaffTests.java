package com.lcl.galaxy.customer.service.provider;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import com.lcl.galaxy.customer.service.provider.mapper.CustomerStaffMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerStaffTests {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Test
    public void testQueryCustomerStaffById() {
        CustomerStaff customerStaff = customerStaffMapper.selectById(1L);

        assertNotNull(customerStaff);
        assertNotNull(customerStaff.getNickname().equals("tianyalan"));
    }
}
