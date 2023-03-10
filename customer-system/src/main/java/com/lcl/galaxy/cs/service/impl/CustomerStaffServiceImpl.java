package com.lcl.galaxy.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.cs.infrastructure.exception.BizException;
import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.integration.OutsourcingSystemClient;
import com.lcl.galaxy.cs.mapper.CustomerStaffMapper;
import com.lcl.galaxy.cs.mapper.MybatisCustomerStaffMapper;
import com.lcl.galaxy.cs.service.ICustomerStaffService;
import com.lcl.galaxy.cs.service.IOutsourcingSystemService;
import com.lcl.galaxy.cs.servicebus.endpoint.CustomerStaffEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStaffServiceImpl extends ServiceImpl<CustomerStaffMapper, CustomerStaff> implements ICustomerStaffService {

//    @Autowired
//    private MybatisCustomerStaffMapper customerStaffMapper;

    // 使用 CustomerStaffMapper 操作数据库
//    @Autowired
//    private CustomerStaffMapper customerStaffMapper;
//
//
//    public CustomerStaff findCustomerStaffById1(Long staffId) {
//        return customerStaffMapper.selectById(staffId);
//    }

    @Autowired
    private IOutsourcingSystemService outsourcingSystemService;
    @Autowired
    private OutsourcingSystemClient outsourcingSystemClient;
    @Autowired
    private CustomerStaffEndpoint customerStaffEndpoint;

    // 使用 baseMapper 操作数据库
    @Override
    public CustomerStaff findCustomerStaffById(Long staffId) {
//        CustomerStaff customerStaff = new CustomerStaff();
//        customerStaff.setStaffName("zhangsan");
//        customerStaff.setAccountId(1234L);
//        try {
//            Thread.sleep(1*1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int i = 1/0;
//        CustomerStaff customerStaff1 = null;
//        customerStaff1.setStaffName("");
        return baseMapper.selectById(staffId);
    }

    // 直接调用父类 ServiceImpl 中的方法
    @Override
    public List<CustomerStaff> findCustomerStaffs() {
        return this.list();
    }

    @Override
    public Boolean createCustomerStaff(CustomerStaff customerStaff) throws BizException {
        return this.save(customerStaff);
    }

    @Override
    public Boolean updateCustomerStaff(CustomerStaff customerStaff) {
        return this.updateById(customerStaff);
    }

    @Override
    public Boolean deleteCustomerStaffById(Long staffId) {
        /*
        // 使用更新的方式实现逻辑删除
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setIsDeleted(true);
        return this.updateById(customerStaff);
        */
        // 使用Mybatis-Plus自带的逻辑删除，直接调用删除方法，会被拦截，然后更新
        return this.removeById(staffId);
    }

    @Override
    public void syncGetOutsourcingCustomerStaffBySystemId(Long systemId) {
        // 获取租户信息
        OutsourcingSystem outsourcingSystem = outsourcingSystemService.findOutsourcingSystemById(systemId);
        // 根据租户信息远程获取客服信息
        // List<CustomerStaff> customerStaffs = outsourcingSystemClient.getCustomerStaffs(outsourcingSystem);

        List<CustomerStaff> customerStaffs = customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystem);
        // 批量保存
        this.saveBatch(customerStaffs);
    }

    @Override
    public PageObject<CustomerStaff> findCustomerStaffs(Long pageSize, Long pageIndex) {
        return this.getCustomerStaffsPageObject(null, pageSize, pageIndex);
    }

    @Override
    public PageObject<CustomerStaff> findCustomerStaffsByName(String staffName, Long pageSize, Long pageIndex) {
        return this.getCustomerStaffsPageObject(staffName, pageSize, pageIndex);
    }

    private PageObject<CustomerStaff> getCustomerStaffsPageObject(String staffName, Long pageSize, Long pageIndex) {
        // 组装业务查询条件
        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper();
        // 使用Mybatis-Plus自带的逻辑删除，不需要手动添加该字段的赋值
        //queryWrapper.eq(CustomerStaff::getIsDeleted, false);
        if(StringUtils.isNotBlank(staffName)){
            queryWrapper.like(CustomerStaff::getStaffName, staffName);
        }
        queryWrapper.orderByDesc(CustomerStaff::getCreateTime);

        // 组装分页条件
        IPage<CustomerStaff> page = new Page<>(pageIndex, pageSize);

        // 查询
        IPage<CustomerStaff> pageResult = baseMapper.selectPage(page, queryWrapper);

        // 转换为自定义分页对象
        PageObject<CustomerStaff> pageObject = new PageObject<CustomerStaff>();
        pageObject.buildPage(pageResult.getRecords(), pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize());
        return pageObject;
    }

//    /**
//     * 使用mybatis
//     * @param customerStaff
//     * @return
//     * @throws BizException
//     */
//    @Override
//    public Boolean createCustomerStaff(CustomerStaff customerStaff) throws BizException {
//        customerStaffMapper.creatCustomerStaff(customerStaff);
//        return true;
//    }





}
