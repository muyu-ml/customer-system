package com.lcl.galaxy.microservice.security.cache.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;

import java.util.Arrays;

@Configuration
@EnableCaching
public class SpringAuthCacheConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("userCache")));
        return cacheManager;
    }

    @Bean
    public UserCache userCache(){
        Cache cache = cacheManager().getCache("userCache");
        return new SpringCacheBasedUserCache(cache);
    }

}