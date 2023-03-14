package com.lcl.galaxy.cs.controller.vo;

import com.lcl.galaxy.cs.entity.staff.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCustomerStaffReqVO {
    private Long groupId;
    private String nickname;
    private String accountId;
    private String staffName;
    private String avatar;
    private String phone;
    private Gender gender;
    private String status;
    private String goodAt;
    private String welcomeMessage;
    private String remark;
}
