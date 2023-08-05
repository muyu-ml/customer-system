package com.lcl.galaxy.microservice.security.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;

@Configuration
@EnableCaching
public class EhCacheAuthCacheConfig {


    @Bean
    public EhCacheFactoryBean ehCacheFactoryBean(){
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheFactoryBean;
    }

    @Bean
    public CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        return new EhCacheManagerFactoryBean();
    }


    @Bean
    public UserCache userCache(){
        EhCacheBasedUserCache userCache = new EhCacheBasedUserCache();
        userCache.setCache(ehCacheFactoryBean().getObject());
        return userCache;
    }
}