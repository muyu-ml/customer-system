package com.lcl.galaxy.cs.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo2Handler  {

    @XxlJob("demoHandler")
    public ReturnT<String> execute(String s) throws Exception {
        try {
            log.info("========demo");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            return ReturnT.FAIL;
        }
    }
}
