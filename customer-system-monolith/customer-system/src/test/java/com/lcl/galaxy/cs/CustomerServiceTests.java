package com.lcl.galaxy.cs;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.mapper.CustomerStaffMapper;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CustomerServiceTests {
    @MockBean
    private CustomerStaffMapper customerStaffMapper;

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Autowired
    private Environment environment;


    @Test
    public void testEnvValue(){
        String username = environment.getProperty("spring.datasource.username");
        Assertions.assertEquals("root", username);
    }

    @Test
    public void testFindCustomerStaffById(){
        Long staffId = 1L;
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setStaffName("lcl");
        customerStaff.setIsDeleted(false);

        // 模拟返回一个假象的customerStaff
        Mockito.when(customerStaffMapper.selectById(staffId)).thenReturn(customerStaff);

        CustomerStaff customerStaffById = customerStaffService.findCustomerStaffById(staffId);

        assertThat(customerStaffById).isNotNull();

        assertThat(customerStaffById.getId()).isEqualTo(staffId);

    }
}
