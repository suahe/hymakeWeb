package com.hymake.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hymake.web.config.MarvelConfig;
import com.hymake.web.mapper.BaseConstantMapper;

/**
 * @Description t_base_constant 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-26 16:45:46
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseConstantService")
@Transactional
public class BaseConstantService {

	private static final Logger log = LoggerFactory.getLogger(BaseConstantService.class);
	
	@Autowired
	private BaseConstantMapper baseConstantMapper;
	
	private LoadingCache<String, String> constantCache;
	
	public BaseConstantService(MarvelConfig marvelConfig) {
		this.constantCache = CacheBuilder.newBuilder()
	        .expireAfterWrite(marvelConfig.getCacheConstantDuration(), TimeUnit.MINUTES)
	        .maximumSize(1000)
	        .recordStats()
	        .build(new CacheLoader<String, String>() {
	            @Override
	            public String load(String keyword) throws Exception {
	            	log.debug("初始化缓存："+keyword);
	                return baseConstantMapper.selectValueByKeyword(keyword);
	            }
	        });
	}
	
	/**
	 * @Description 通过keyword获取缓存数据
	 * @param keyword
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 23:14:43
	 */
	public String getCacheValueByKeyword(String keyword) {
		
		String value = null;
		try {
			value = constantCache.get(keyword);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public Map<String, String> getWebSite() {
		
		Map<String, String> map = new HashMap<>();
		map.put("title", getCacheValueByKeyword("WZQC"));
		map.put("keywords", getCacheValueByKeyword("SSGJC"));
		map.put("description", getCacheValueByKeyword("WZKH"));
		map.put("author", getCacheValueByKeyword("ZZ"));
		return map;
		
	}
	
}