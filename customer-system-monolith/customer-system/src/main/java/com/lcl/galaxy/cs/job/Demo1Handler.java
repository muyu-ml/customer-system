//package com.lcl.galaxy.cs.xxljob;
//
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
//import com.xxl.job.core.log.XxlJobLogger;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@JobHandler(value = "demoHandler")
//@Component
//@Slf4j
//public class Demo1Handler extends IJobHandler {
//    @Override
//    public ReturnT<String> execute(String s) throws Exception {
//        XxlJobLogger.log("xxl-job测试任务开始执行。【args: {}】", s);
//        try {
//            log.info("========demo");
//            XxlJobLogger.log("xxl-job测试任务执行结束。");
//            return SUCCESS;
//        } catch (Exception e) {
//            XxlJobLogger.log("xxl-job测试任务执行出错!", e);
//            return FAIL;
//        }
//    }
//}
