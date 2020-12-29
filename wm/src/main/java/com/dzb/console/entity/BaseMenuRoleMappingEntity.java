package com.dzb.console.entity;
/***
 * @Description t_base_menu_role_mapping 表映射实体类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:03
 * @version 1.0
 * @remark create by ca
 */

public class BaseMenuRoleMappingEntity {

	/** 菜单角色映射编号 */
	private String menuRoleMappingId;
	/** 菜单编号 */
	private String menuId;
	/** 角色编号 */
	private String roleId;
	
	/** 菜单角色映射编号 */
	public String getMenuRoleMappingId(){
		return menuRoleMappingId;
	}

	/** 菜单角色映射编号 */
	public void setMenuRoleMappingId(String menuRoleMappingId){
		this.menuRoleMappingId = menuRoleMappingId;
	}
	
	/** 菜单编号 */
	public String getMenuId(){
		return menuId;
	}

	/** 菜单编号 */
	public void setMenuId(String menuId){
		this.menuId = menuId;
	}
	
	/** 角色编号 */
	public String getRoleId(){
		return roleId;
	}

	/** 角色编号 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
}