package com.lcl.galaxy.cs;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerWithAutoConfigureMockMvc {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICustomerStaffService customerStaffService;

    @Test
    public void test() throws Exception{
        Long staffId = 3L;
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setStaffName("lcl");
        customerStaff.setIsDeleted(false);
        given(customerStaffService.findCustomerStaffById(staffId)).willReturn(customerStaff);
        mockMvc.perform(MockMvcRequestBuilders.get("/customerStaffs/"+staffId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
