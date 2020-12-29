package com.dzb.console.entity;
/***
 * @Description t_base_user_role_mapping 表映射实体类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:21
 * @version 1.0
 * @remark create by ca
 */

public class BaseUserRoleMappingEntity {

	/** 用户角色映射编号 */
	private String userRoleMappingId;
	/** 角色编号 */
	private String roleId;
	/** 用户编号 */
	private String userId;
	/** 用户名称 */
	private String name;
	/** 账户 */
	private String username;
	
	/** 用户角色映射编号 */
	public String getUserRoleMappingId(){
		return userRoleMappingId;
	}

	/** 用户角色映射编号 */
	public void setUserRoleMappingId(String userRoleMappingId){
		this.userRoleMappingId = userRoleMappingId;
	}
	
	/** 角色编号 */
	public String getRoleId(){
		return roleId;
	}

	/** 角色编号 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/** 账户 */
	public String getUsername() {
		return username;
	}

	/** 账户 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 用户姓名 */
	public String getName() {
		return name;
	}
	
	/** 用户姓名 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}