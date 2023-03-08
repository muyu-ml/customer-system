package com.lcl.galaxy.cs.controller.webmvc;

import com.lcl.galaxy.cs.controller.vo.AddCustomerStaffReqVO;
import com.lcl.galaxy.cs.controller.vo.UpdateCustomerStaffReqVO;
import com.lcl.galaxy.cs.controller.vo.CustomerStaffRespVO;
import com.lcl.galaxy.cs.converter.CustomerStaffConverter;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.infrastructure.vo.Result;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customerStaffs")
public class CustomerStaffController {


    @Autowired
    private ICustomerStaffService customerStaffService;

    //新增CustomerStaff
    @PostMapping("/")
    public Result<Long> addCustomerStaff(@RequestBody AddCustomerStaffReqVO addCustomerStaffReqVO) {

        //数据转换
        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertCreateReq(addCustomerStaffReqVO);

        //调用Service层完成操作
        customerStaffService.createCustomerStaff(customerStaff);

        return Result.success(customerStaff.getId());
    }

    @PutMapping("/")
    public Result<Boolean> updateCustomerStaff(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }

    @PutMapping("/status")
    public Result<Boolean> updateCustomerStaffStatus(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }

    @GetMapping("/{staffId}")
    public Result<CustomerStaffRespVO> findCustomerStaffById(@PathVariable("staffId") Long staffId) {

        CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);

        CustomerStaffRespVO customerStaffRespVO = CustomerStaffConverter.INSTANCE.convertResp(customerStaff);

        return Result.success(customerStaffRespVO);
    }

    /**
     * 根据系统id获取客服x
     * @param systemId
     * @return
     */
    @GetMapping("/sync/{systemId}")
    public Result<Boolean> findCustomerStaffBySystemId(@PathVariable("systemId") Long systemId) {

        customerStaffService.syncGetOutsourcingCustomerStaffBySystemId(systemId);

        return Result.success(true);
    }

    @GetMapping("/async/{staffId}")
    public WebAsyncTask<CustomerStaffRespVO> asyncFindCustomerStaffById(@PathVariable("staffId") Long staffId) {

        System.out.println("main thread: " + Thread.currentThread().getName());

        WebAsyncTask<CustomerStaffRespVO> task = new WebAsyncTask<>(5*1000L, ()->{
            System.out.println("thread: " + Thread.currentThread().getName());
            try{
                CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);
                CustomerStaffRespVO customerStaffRespVO = CustomerStaffConverter.INSTANCE.convertResp(customerStaff);
                return customerStaffRespVO;
            }catch (RuntimeException e){
                throw new RuntimeException();
            }
//            CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);
//            CustomerStaffRespVO customerStaffRespVO = CustomerStaffConverter.INSTANCE.convertResp(customerStaff);
//            return customerStaffRespVO;
        });

        // 超时
        task.onTimeout(() -> {
            System.out.println(Thread.currentThread().getName() + ": timeOut");
            return new CustomerStaffRespVO();
        });

        // 异常
        task.onError(() -> {
            System.out.println(Thread.currentThread().getName() + ": error");
            return new CustomerStaffRespVO();
        });

        // 完成
        task.onCompletion(() -> {
            System.out.println(Thread.currentThread().getName() + ": ok");
        });

        // 继续执行
        System.out.println(Thread.currentThread().getName() + ": continue");

        return task;
    }

    @DeleteMapping("/{staffId}")
    public Result<Boolean> deleteCustomerStaffById(@PathVariable("staffId") Long staffId) {

        Boolean isDeleted = customerStaffService.deleteCustomerStaffById(staffId);

        return Result.success(isDeleted);
    }


//    @GetMapping("/future/async/{staffId}")
//    public Result<CustomerStaffRespVO> asyncfutureFindCustomerStaffById(@PathVariable("staffId") Long staffId) {
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
//            System.out.println("电饭煲开始做饭");
//            return "白米饭";
//        });
//
//        System.out.println("我先去搞点牛奶和鸡蛋");
//        System.out.println("main thread: " + Thread.currentThread().getName());
//
//    }


    @GetMapping("/page/{pageSize}/{pageIndex}")
    public Result<PageObject<CustomerStaffRespVO>> findCustomerStaffs(@PathVariable("pageSize") Long pageSize, @PathVariable("pageIndex") Long pageIndex) {

        PageObject<CustomerStaff> pagedCustomerStaff = customerStaffService.findCustomerStaffs(pageSize, pageIndex);

        List<CustomerStaffRespVO> customerStaffRespVOs = CustomerStaffConverter.INSTANCE.convertListResp(pagedCustomerStaff.getList());

        PageObject<CustomerStaffRespVO> result = new PageObject<CustomerStaffRespVO>()
                .setList(customerStaffRespVOs)
                .setTotal(pagedCustomerStaff.getTotal())
                .setPageIndex(pagedCustomerStaff.getPageIndex())
                .setPageSize(pagedCustomerStaff.getPageSize());

        return Result.success(result);
    }
}
