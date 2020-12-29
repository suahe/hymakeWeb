package com.dzb.console.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.config.MarvelConfig;
import com.dzb.console.entity.BaseConstantEntity;
import com.dzb.console.mapper.BaseConstantMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

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
	        .expireAfterWrite(marvelConfig.getCacheConstantDuration(), TimeUnit.MINUTES)		//设置10分钟后清除缓存
	        .maximumSize(1000)
	        .recordStats()
	        .build(new CacheLoader<String, String>() {		//设置缓存（Map）的key和value值
	            @Override
	            public String load(String keyword) throws Exception {
	                return baseConstantMapper.selectValueByKeyword(keyword);
	            }
	        });
	}
	
	/**
	 * @Description 通过keyword获取缓存数据
	 * @param keyword
	 * @return
	 * @author 38840472@qq.com
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
	
	/**
	 * @Description 查看缓存状态
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:25:29
	 */
	public Map<String, Long> stats() {
		
		CacheStats cacheStats = constantCache.stats();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("缓存命中次数(hitCount)", cacheStats.hitCount());
		map.put("缓存未命中次数(missCount)", cacheStats.missCount());
		map.put("缓存加载成功次数(loadSuccessCount)", cacheStats.loadSuccessCount());
		map.put("缓存加载异常次数(loadExceptionCount)", cacheStats.loadExceptionCount());
		map.put("总加载时间:纳秒(totalLoadTime)", cacheStats.totalLoadTime());
		map.put("缓存自动回收次数(evictionCount)", cacheStats.evictionCount());
		return map;
	}
	
	/**
	 * @Description 清空指定keyword的缓存
	 * @param keyword
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:15:16
	 */
	public void invalidate(String keyword) {
		
		constantCache.invalidate(keyword);
	}
	
	/**
	 * @Description 清空所有缓存
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:15:31
	 */
	public void invalidateAll() {
		
		constantCache.invalidateAll();
	}
	
	/**
	 * @Description 获取所有的缓存数据
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:15:44
	 */
	public Map<String, String>  asMap() {
		
		return constantCache.asMap();
	}
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	public PageInfo<BaseConstantEntity> getPagination(Integer offset, Integer limit, BaseConstantEntity baseConstantEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<BaseConstantEntity>(baseConstantMapper.selectPagination(baseConstantEntity));
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param constantId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	public BaseConstantEntity getByPkey(long constantId){
	
		log.debug("通过主键查询信息");
		return baseConstantMapper.selectByPkey(constantId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	public int insert(BaseConstantEntity baseConstantEntity) {

		log.debug("新增信息");
		baseConstantEntity.setConstantId(IDUtil.getSnowflakeId());
		baseConstantEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseConstantEntity.setUpdateTime(new Date());
		return baseConstantMapper.insert(baseConstantEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	public int update(BaseConstantEntity baseConstantEntity){
	
		log.debug("编辑信息");
		baseConstantEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseConstantEntity.setUpdateTime(new Date());
		return baseConstantMapper.update(baseConstantEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param keyword
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-28 14:15:07
	 */
	public int update(String keyword, String value){
		
		log.debug("编辑信息");
		BaseConstantEntity baseConstantEntity = new BaseConstantEntity();
		baseConstantEntity.setKeyword(keyword);
		baseConstantEntity.setValue(value);
		baseConstantEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseConstantEntity.setUpdateTime(new Date());
		return baseConstantMapper.updateKeywordAndValue(baseConstantEntity);
	}
	
	/**
	 * @Description 编辑信息同时更新缓存
	 * @param keyword
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-28 14:18:09
	 */
	public int updateCache(String keyword, String value){
		
		log.debug("编辑信息同时更新缓存");
		int i = update(keyword, value);
		constantCache.invalidate(keyword);
		return i;
	}
	
	/**
	 * @Description 删除信息
	 * @param constantId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	public int delete(long constantId){
	
		log.debug("删除信息");
		return baseConstantMapper.delete(constantId);
	}
	
	/**
	 * @Description 通过keyword查询是否已经存在
	 * @param keyword
	 * @param count
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 18:06:28
	 */
	public boolean countByKeyword(String keyword, int count) {
		
		log.debug("通过keyword查询是否已经存在");
		int i = baseConstantMapper.countByKeyword(keyword);
		return i < count ? true : false;
	}
	
}