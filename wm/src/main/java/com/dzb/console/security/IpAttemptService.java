package com.dzb.console.security;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.console.config.MarvelConfig;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @Description 登录尝试服务类（多次登录实现拦截IP）
 * @author 38840472@qq.com
 * @date 2020-04-30 09:35:20
 */

@Service("IpAttemptService")
public class IpAttemptService {
	
	private static final Logger log = LoggerFactory.getLogger(IpAttemptService.class);
	
	@Autowired 
	private MarvelConfig marvelConfig;
	 
	private LoadingCache<String, Integer> attemptCache;
	
	public IpAttemptService(MarvelConfig marvelConfig) {
		
		Integer expireTime = marvelConfig.getExpireTime();
		log.debug("配置缓存器刷新时间："+expireTime+"分钟");
        this.attemptCache = CacheBuilder.newBuilder()
	        .expireAfterWrite(expireTime, TimeUnit.MINUTES)		//设置10分钟后清除缓存
	        .build(new CacheLoader<String, Integer>() {		//设置缓存（Map）的key和value值
	            @Override
	            public Integer load(String s) throws Exception {
	                return 0;
	            }
	        });
    }
	
	/**
	 * @Description 获取所有的缓存数据
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-22 16:49:39
	 */
	public Map<String, Integer> getAllCache(){
		
		log.debug("查询所有缓存信息");
		return attemptCache.asMap();
	}
	
	/**
	 * @Description 清除指定缓存
	 * @param ip
	 * @author 38840472@qq.com
	 * @date 2020-04-22 16:03:31
	 */
	public void loginSucceeded(String ip) {
		
		log.debug("清除IP："+ ip +"缓存信息");
        attemptCache.invalidate(ip);
    }

	/**
	 * @Description 当前IP失败计数增加1
	 * @param ip
	 * @author 38840472@qq.com
	 * @date 2020-04-22 15:19:19
	 */
    public void loginFailed(String ip){
    	
        int attempts = 0;
		try {
			attempts = attemptCache.get(ip).intValue();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        attempts++;
        attemptCache.put(ip, attempts);
        log.debug("增加IP：" + ip + " 累计登录失败计数：" + attempts + "次");
    }

    /**
     * @Description 当前IP是否被锁定
     * @param ip
     * @return
     * @author 38840472@qq.com
     * @date 2020-04-22 15:15:36
     */
    public boolean isBlocked(String ip){
    	
    	int count = getAttemptsCount(ip);
    	boolean b = (count >= marvelConfig.getMaxAttempt()) ? true : false;
		log.debug("IP：" + ip + " 锁定状态：" + b );
    	return b;
    }

    /**
     * @Description 获取当前IP错误尝试次数
     * @param ip
     * @return
     * @author 38840472@qq.com
     * @date 2020-04-23 17:03:21
     */
    public int getAttemptsCount(String ip) {
    	
    	int count = 0;
    	try {
			count = attemptCache.get(ip).intValue();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	log.debug("IP：" + ip + "尝试错误次数：" + count );
    	return count;
    }

}
