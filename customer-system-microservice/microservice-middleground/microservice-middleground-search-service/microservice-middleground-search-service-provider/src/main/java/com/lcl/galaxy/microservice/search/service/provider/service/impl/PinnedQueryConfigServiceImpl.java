package com.lcl.galaxy.microservice.search.service.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.galaxy.microservice.search.service.provider.entity.PinnedQueryConfig;
import com.lcl.galaxy.microservice.search.service.provider.mapper.PinnedQueryConfigMapper;
import com.lcl.galaxy.microservice.search.service.provider.service.PinnedQueryConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PinnedQueryConfigServiceImpl extends ServiceImpl<PinnedQueryConfigMapper, PinnedQueryConfig> implements PinnedQueryConfigService {

	@Autowired
	private PinnedQueryConfigMapper pinnedQueryConfigMapper;

	@Override
	public PinnedQueryConfig findActivePinnedQueryConfigBySubjectWord(String subjectWord, Integer businessType) {
		PinnedQueryConfig pinnedQueryConfig = pinnedQueryConfigMapper.findActivePinnedQueryConfigBySubjectWord(businessType, subjectWord);

		if(!Objects.isNull(pinnedQueryConfig)) {
			log.info("获取PinnedQueryConfig：" + pinnedQueryConfig.toString());
		}
		return pinnedQueryConfig;
	}

}
