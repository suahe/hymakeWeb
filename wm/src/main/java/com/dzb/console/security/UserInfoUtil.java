package com.dzb.console.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *@Description:结合Spring-Security框架，提供用户信息及扩展信息查询（可自行扩展方法）
 *@Author:38840472@qq.com
 *@date:2017年5月26日下午3:09:09
 */

public class UserInfoUtil {
	
	private final static String ANONYMOUS = "ANONYMOUS";
	private static UserInfoEntity userInfoEntity = null;
	
	/**
	 * @Description 获取当前用户信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 11:00:06
	 */
	public static UserInfoEntity getUserInfo() {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(null == authentication) {
				userInfoEntity = null;
			}else {
				userInfoEntity = (UserInfoEntity) authentication.getPrincipal();
			}
		} catch (java.lang.ClassCastException e) {
			userInfoEntity = null;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return userInfoEntity;
	}
	
	/**
	 * @Description 获取当前用户账号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 11:00:53
	 */
	public static String getUsername() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return ANONYMOUS;
		} else {
			return userDetails.getUsername();
		}
	}
	

	/**
	 * @Description 获取当前用户名
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 11:00:29
	 */
	public static String getName() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return ANONYMOUS;
		} else {
			return userDetails.getName();
		}
	}
	
	/**
	 * @Description 获取用户权限信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 11:01:13
	 */
	public Collection<GrantedAuthority> getAuthorities() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return null;
		} else {
			return userDetails.getAuthorities();
		}
	}
	
	/**
	 * @Description 获取当前用户ID
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 11:04:05
	 */
	public static String getUserId() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return ANONYMOUS;
		} else {
			return userDetails.getUserId();
		}
	}
	
	/**
	 * @Description 获取当前用户角色列表
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-26 11:24:52
	 */
	public static List<String> getRoleIds() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return null;
		} else {
			return userDetails.getRoleIds();
		}
	}
	
	/**
	 * @Description:获取用户登录ID，用于在需要用户行为溯源时使用。
	 * @return
	 */
	public static String getUserlogId() {
		
		UserInfoEntity userDetails = getUserInfo();
		if (null == userDetails) {
			return ANONYMOUS;
		} else {
			return userDetails.getUserlogId();
		}
	}
	
}
