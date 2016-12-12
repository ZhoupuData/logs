package com.zhoupu.zplogsbatch.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 上午8:05:27
 * @version 1.0 *
 * @since
 */
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CaffeineCache caffeineCache() {
		CaffeineCache cache = new CaffeineCache("phonecode", Caffeine.newBuilder().recordStats()
				.expireAfterWrite(60 * 2, TimeUnit.SECONDS).maximumSize(50000).build());

		return cache;
	}
}
