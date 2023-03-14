package com.lcl.galaxy.outsouring.shanghai.controller.vo.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShanghaiCustomerStaffUpdateReqVO {

    private Long id;
    private String nickname;
    private String avatar;
    private String goodAt;
    private String remark;
}
