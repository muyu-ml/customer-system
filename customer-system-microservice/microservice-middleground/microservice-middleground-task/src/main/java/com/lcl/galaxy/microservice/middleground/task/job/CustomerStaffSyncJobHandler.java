package com.lcl.galaxy.microservice.middleground.task.job;

import com.lcl.galaxy.microservice.middleground.task.client.CustomerStaffSyncClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerStaffSyncJobHandler {
//
//    @Autowired
//    private CustomerStaffServiceClient customerStaffServiceClient;

    @Autowired
    private CustomerStaffSyncClient customerStaffSyncClient;

    @XxlJob("syncCustomerStaffJob")
    public ReturnT<String> syncCustomerStaff() throws Exception{
        log.info("{} ====== syncCustomerStaffJob ====== 同步客服信息");
        // 使用钩子函数获取参数
        String param = XxlJobHelper.getJobParam();
        Long systemId = Long.parseLong(param);
        // 远程调用，获取客服信息并保存
        customerStaffSyncClient.syncOutsourcingCustomerStaffsBySystemId(systemId);
        return ReturnT.SUCCESS;
    }
}
