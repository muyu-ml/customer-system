package com.lcl.galaxy.search.service.provider.ikremote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface DictionaryMapper extends JpaRepository<ExtensionWord, Long>  {

	@Query("select MAX(e.updateTime) from ExtensionWord e")
	Date findLastedUpdateTime();
	
	@Query("select e from ExtensionWord e where e.updateTime > ?1")
	List<ExtensionWord> findByUpdateTime(Date lastModified);
}
