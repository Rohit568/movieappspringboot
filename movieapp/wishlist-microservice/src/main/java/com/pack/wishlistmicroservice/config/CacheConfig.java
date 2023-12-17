package com.pack.wishlistmicroservice.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@AutoConfigureBefore(CacheAutoConfiguration.class)
public class CacheConfig {
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("movies");
    }

}
