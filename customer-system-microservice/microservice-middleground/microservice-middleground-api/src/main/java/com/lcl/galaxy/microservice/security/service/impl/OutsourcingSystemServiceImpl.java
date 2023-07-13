package com.lcl.galaxy.microservice.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.page.PageObject;
import com.lcl.galaxy.microservice.security.entity.tenant.OutsourcingSystem;
import com.lcl.galaxy.microservice.security.mapper.OutsourcingSystemMapper;
import com.lcl.galaxy.microservice.security.service.IOutsourcingSystemService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig(cacheNames = "outsourcing-system-object")
public class OutsourcingSystemServiceImpl extends ServiceImpl<OutsourcingSystemMapper, OutsourcingSystem> implements IOutsourcingSystemService {
    @Override
    public List<OutsourcingSystem> findAllOutsourcingSystems() {
        LambdaQueryWrapper<OutsourcingSystem> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    @Cacheable(value = "pageObject", key = "#root.targetClass + '_' + #p0 + '_' + #p1")
    public PageObject<OutsourcingSystem> findPagedOutsourcingSystems(Long pageSize, Long pageIndex) {
        PageObject<OutsourcingSystem> pageObject = new PageObject<>();
        IPage<OutsourcingSystem> pageResult = baseMapper.findPagedOutsourcingSystems(pageSize, pageIndex);

        pageObject.buildPage(pageResult.getRecords(), pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize());
        return pageObject;
    }

    @Override
    @Cacheable(key = "#root.targetClass + '_' + #systemId")
    public OutsourcingSystem findOutsourcingSystemById(Long systemId) {
        return baseMapper.selectById(systemId);
    }

    @Override
    @CachePut(key = "#root.targetClass + '_' + #outsourcingSystem.id")
    public Boolean addOutsourcingSystem(OutsourcingSystem outsourcingSystem) {
        return this.save(outsourcingSystem);
    }

    @Override
    @CachePut(key = "#root.targetClass + '_' + #outsourcingSystem.id")
    public Boolean updateOutsourcingSystem(OutsourcingSystem outsourcingSystem) {
        return this.updateById(outsourcingSystem);
    }

    @Override
    @CacheEvict(key = "#root.targetClass + '_' + #systemId")
    public Boolean deleteOutsourcingSystemById(Long systemId) {
        baseMapper.deleteById(systemId);
        return true;
    }
}
