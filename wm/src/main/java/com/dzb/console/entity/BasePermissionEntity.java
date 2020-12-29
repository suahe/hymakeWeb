package com.dzb.console.entity;

import java.util.ArrayList;
import java.util.List;

/** *
 * @Description 权限资源树实体
 * @author 38840472@qq.com
 * @date 2020-04-24 08:34:56
 */
public class BasePermissionEntity {

	/** 菜单名称 */
	private String menuName;
	/** 资源信息 */
	private String url;
	/** 角色编号 */
	private List<String> roleIds = new ArrayList<String>();
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	
	
}
