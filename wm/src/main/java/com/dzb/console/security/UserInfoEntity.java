package com.dzb.console.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/***
 * @Description 用户信息实体
 * @author 38840472@qq.com
 * @date 2020-04-24 08:41:25
 */

public class UserInfoEntity extends User{

	private static final long serialVersionUID = -753564264801538182L;
	
	/** 用户编号 */
	private String userId;
	/** 用户名称  */
	private String name;
	/** 角色列表 */
	private List<String> roleIds;
	/** 日志编号（记录登录的信息） */
	private String userlogId;
	/** 公网IP地址 */
	private String cip;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public String getUserlogId() {
		return userlogId;
	}

	public void setUserlogId(String userlogId) {
		this.userlogId = userlogId;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	/**
	 * 用户信息
	 * @param username用户名
	 * @param password密码
	 * @param enabled账号是否存在
	 * @param accountNonExpired账号是否过期
	 * @param credentialsNonExpired 账号凭证是否过期
	 * @param accountNonLocked账号是否被锁定
	 * @param authorities权限列表
	 */
	public UserInfoEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
