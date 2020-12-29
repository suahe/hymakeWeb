package com.dzb.console.entity;
/***
 * @Description t_base_menu 表映射实体类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:00:36
 * @version 1.0
 * @remark create by ca
 */

public class BaseMenuEntity {

	/** 菜单编号 */
	private String menuId;
	/** 菜单名称 */
	private String menuName;
	/** 图标样式 */
	private String iconCss;
	/** 资源信息 */
	private String url;
	/** 菜单信息父编号 */
	private String menuPid;
	/** 排序 */
	private Integer sequ;
	/** 有效状态 */
	private Integer isDel;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private java.util.Date updateTime;
	
	/** 菜单编号 */
	public String getMenuId(){
		return menuId;
	}

	/** 菜单编号 */
	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	/** 菜单名称 */
	public String getMenuName(){
		return menuName;
	}

	/** 菜单名称 */
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	
	/** 图标样式 */
	public String getIconCss(){
		return iconCss;
	}

	/** 图标样式 */
	public void setIconCss(String iconCss){
		this.iconCss = iconCss;
	}
	
	/** 资源信息 */
	public String getUrl(){
		return url;
	}

	/** 资源信息 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/** 菜单信息父编号 */
	public String getMenuPid(){
		return menuPid;
	}

	/** 菜单信息父编号 */
	public void setMenuPid(String menuPid){
		this.menuPid = menuPid;
	}
	
	/** 排序 */
	public Integer getSequ(){
		return sequ;
	}

	/** 排序 */
	public void setSequ(Integer sequ){
		this.sequ = sequ;
	}
	
	/** 有效状态 */
	public Integer getIsDel(){
		return isDel;
	}

	/** 有效状态 */
	public void setIsDel(Integer isDel){
		this.isDel = isDel;
	}
	
	/** 创建人 */
	public String getCreateUserid(){
		return createUserid;
	}

	/** 创建人 */
	public void setCreateUserid(String createUserid){
		this.createUserid = createUserid;
	}
	
	/** 创建时间 */
	public java.util.Date getCreateTime(){
		return createTime;
	}

	/** 创建时间 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	
	/** 更新人 */
	public String getUpdateUserid(){
		return updateUserid;
	}

	/** 更新人 */
	public void setUpdateUserid(String updateUserid){
		this.updateUserid = updateUserid;
	}
	
	/** 更新时间 */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}

	/** 更新时间 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
	/**
	 * 重载方法，通过主键（menuId）判断两个对象是否一致
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof BaseMenuEntity) {
			BaseMenuEntity baseMenuEntity = (BaseMenuEntity) object;
			if (this.menuId.equals(baseMenuEntity.getMenuId())) {
				return true;
			}
		}
		return false;
	}
	
	
}