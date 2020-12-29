package com.dzb.console.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

/**
 * @Description 刷新权限缓存
 * @author 38840472@qq.com
 * @date 2020-04-24 08:40:55
 */

@Service
public class RefreshResourceService extends ApplicationObjectSupport {
	
	private static final Logger log = LoggerFactory.getLogger(RefreshResourceService.class);

	public boolean refreshCache(){
		
		log.debug("刷新权限缓存 start");
		try {
			ApplicationContext ctx =  getApplicationContext();
			WebFilterInvocationSecurityMetadataSource webFilterInvocationSecurityMetadataSource = (WebFilterInvocationSecurityMetadataSource)ctx.getBean("webFilterInvocationSecurityMetadataSource");
			webFilterInvocationSecurityMetadataSource.loadResourceDefine();
		} catch (Exception e) {
			log.debug("刷新权限缓存 失败");
			e.printStackTrace();
			return false;
		}
		log.debug("刷新权限缓存 end");
		return true;
	}
}
