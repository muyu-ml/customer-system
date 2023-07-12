package com.lcl.galaxy.microservice.search.service.provider.service;

import com.lcl.galaxy.microservice.search.service.provider.entity.PinnedQueryConfig;

public interface PinnedQueryConfigService {

	//用于前台查询
	PinnedQueryConfig findActivePinnedQueryConfigBySubjectWord(String subjectWord, Integer businessType);
}
