package com.lcl.projects.cs;

import com.lcl.galaxy.microservice.middleground.api.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.api.mapper.CustomerStaffMapper;
import com.lcl.galaxy.microservice.middleground.api.service.ICustomerStaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CustomerStaffServiceTests {

    @MockBean
    private CustomerStaffMapper customerStaffMapper;

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Test
    public void testFindCustomerStaffById() {

        Long staffId = 1L;

        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setNickname("tianyalan");
        customerStaff.setIsDeleted(false);

        //模拟返回一个假想的customerStaff
        Mockito.when(customerStaffMapper.selectById(staffId)).thenReturn(customerStaff);

        CustomerStaff actual = customerStaffService.findCustomerStaffById(staffId);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(staffId);
    }

}
