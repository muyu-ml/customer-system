package com.lcl.galaxy.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.cs.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.cs.infrastructure.page.PageObject;
import com.lcl.galaxy.cs.mapper.OutsourcingSystemMapper;
import com.lcl.galaxy.cs.service.IOutsourcingSystemService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OutsourcingSystemServiceImpl extends ServiceImpl<OutsourcingSystemMapper, OutsourcingSystem> implements IOutsourcingSystemService {
    @Override
    public List<OutsourcingSystem> findAllOutsourcingSystems() {
        LambdaQueryWrapper<OutsourcingSystem> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public PageObject<OutsourcingSystem> findPagedOutsourcingSystems(Long pageSize, Long pageIndex) {
        PageObject<OutsourcingSystem> pageObject = new PageObject<>();
        IPage<OutsourcingSystem> pageResult = baseMapper.findPagedOutsourcingSystems(pageSize, pageIndex);

        pageObject.buildPage(pageResult.getRecords(), pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize());
        return pageObject;
    }

    @Override
    public OutsourcingSystem findOutsourcingSystemById(Long systemId) {
        return baseMapper.selectById(systemId);
    }

    @Override
    public Boolean addOutsourcingSystem(OutsourcingSystem outsourcingSystem) {
        return this.save(outsourcingSystem);
    }

    @Override
    public Boolean updateOutsourcingSystem(OutsourcingSystem outsourcingSystem) {
        return this.updateById(outsourcingSystem);
    }

    @Override
    public Boolean deleteOutsourcingSystemById(Long systemId) {
        baseMapper.deleteById(systemId);
        return true;
    }
}
