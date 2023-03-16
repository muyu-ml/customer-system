package com.lcl.galaxy.customer.service.provider.controller.vo;

import com.lcl.galaxy.customer.service.provider.entity.staff.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerStaffReqVO {
    private Long id;
    private Long groupId;
    private String nickname;
    private String avatar;
    @NotNull(message = "状态不能为空")
    private Status status;
    private String goodAt;
    private String welcomeMessage;
    private String remark;
}
