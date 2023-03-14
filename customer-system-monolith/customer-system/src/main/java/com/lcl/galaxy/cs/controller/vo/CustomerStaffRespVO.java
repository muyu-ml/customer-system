package com.lcl.galaxy.cs.controller.vo;

import com.lcl.galaxy.cs.entity.staff.enums.Gender;
import com.lcl.galaxy.cs.entity.staff.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStaffRespVO {
    private Long id;
    private Long groupId;
    private String nickname;
    private String staffName;
    private String avatar;
    private String phone;
    private Gender gender;
    private String goodAt;
    private String welcomeMessage;
    private String remark;
    private Status status;
    private LocalDateTime createTime;
}
