package com.dzb.console.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.dzb.console.entity.BasePermissionEntity;
import com.dzb.console.service.BasePermissionService;

/**
 * @Description 认证资源判定器
 * @author 38840472@qq.com
 * @date 2020-04-24 08:49:41
 */

@Service
public class WebFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private BasePermissionService basePermissionService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	public WebFilterInvocationSecurityMetadataSource(BasePermissionService basePermissionService) {
		
		this.basePermissionService = basePermissionService;
		loadResourceDefine();
		
	}
	
	public void loadResourceDefine(){
		//System.out.println("--------获取数据库配置---------");
		Collection<ConfigAttribute> array;
		ConfigAttribute cfg;
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>(16);
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("BASE");
		atts.add(ca);
		resourceMap.put("/main/load", atts);
		List<BasePermissionEntity> list = basePermissionService.getPermissions();
		for(BasePermissionEntity basePermissionEntity:list) {
			array = new ArrayList<>();
			for(String roleId:basePermissionEntity.getRoleIds()) {
				cfg = new SecurityConfig(roleId);
				array.add(cfg);
				
			}
			
			String url = basePermissionEntity.getUrl();
			url = url.replace("../", "/");
			if(null != url) {
				resourceMap.put(url, array);
//				System.out.println("Url："+url+" 角色列表："+array);
			}
		}
		
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		if(null == resourceMap) {
			loadResourceDefine();
		}
		FilterInvocation filterInvocation = (FilterInvocation) object;
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resUrl = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resUrl);
			//System.out.println("请求的地址为："+filterInvocation.getHttpRequest().getRequestURI());
			//System.out.println("迭代的地址为："+requestMatcher);
			if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
				 return resourceMap.get(resUrl);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
