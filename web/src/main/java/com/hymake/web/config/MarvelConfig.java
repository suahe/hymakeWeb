package com.hymake.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 框架个性化配置
 * @author daizb@hymake.com
 * @date 2020-04-22 16:22:22
 */
@Component
public class MarvelConfig {

	/**
	 * 设置系统常量缓存的时间（分钟）
	 */
	@Value("${marvel.cache.constant.duration}")
    private Integer cacheConstantDuration;
	
	/**
	 * 设置数据字典缓存的时间（分钟）
	 */
	@Value("${marvel.cache.basetype.duration}")
    private Integer cacheBasetypeDuration;

	public Integer getCacheConstantDuration() {
		return cacheConstantDuration;
	}

	public void setCacheConstantDuration(Integer cacheConstantDuration) {
		this.cacheConstantDuration = cacheConstantDuration;
	}

	public Integer getCacheBasetypeDuration() {
		return cacheBasetypeDuration;
	}

	public void setCacheBasetypeDuration(Integer cacheBasetypeDuration) {
		this.cacheBasetypeDuration = cacheBasetypeDuration;
	}
	
}
