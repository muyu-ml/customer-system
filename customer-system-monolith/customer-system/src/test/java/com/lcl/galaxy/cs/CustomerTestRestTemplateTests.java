package com.lcl.galaxy.cs;

import com.lcl.galaxy.cs.controller.vo.CustomerStaffRespVO;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerTestRestTemplateTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ICustomerStaffService customerStaffService;

    @Test
    public void test(){
        Long staffId = 3L;
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setStaffName("lcl");
        customerStaff.setIsDeleted(false);
        given(customerStaffService.findCustomerStaffById(staffId)).willReturn(customerStaff);
        // Result<CustomerStaffRespVO> customerStaffRespVOResult = restTemplate.getForObject("/customerStaffs/"+staffId, Result.class);
        ResponseEntity<Result<CustomerStaffRespVO>> resultResponseEntity = restTemplate.exchange(
                "/customerStaffs/" + staffId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<Result<CustomerStaffRespVO>>() {}
                );
        CustomerStaffRespVO customerStaffRespVO = resultResponseEntity.getBody().getData();
        assertThat(customerStaffRespVO.getId()).isEqualTo(customerStaff.getId());
    }
}
