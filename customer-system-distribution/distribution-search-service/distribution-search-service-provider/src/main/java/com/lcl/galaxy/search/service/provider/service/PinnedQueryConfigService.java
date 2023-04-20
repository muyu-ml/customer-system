package com.lcl.galaxy.search.service.provider.service;

import com.lcl.galaxy.search.service.provider.entity.PinnedQueryConfig;

public interface PinnedQueryConfigService {

	//用于前台查询
	PinnedQueryConfig findActivePinnedQueryConfigBySubjectWord(String subjectWord, Integer businessType);
}
