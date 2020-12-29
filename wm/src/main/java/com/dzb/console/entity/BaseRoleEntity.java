package com.dzb.console.entity;
/***
 * @Description t_base_role 表映射实体类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:15
 * @version 1.0
 * @remark create by ca
 */

public class BaseRoleEntity {

	/** 角色编号 */
	private String roleId;
	/** 角色名称 */
	private String roleName;
	/** 角色父编号 */
	private String rolePid;
	/** 排序 */
	private Integer sequ;
	/** 有效状态 */
	private Integer isDel;
	/** 叶子节点*/
	private int isLeaf;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private java.util.Date updateTime;
	
	/** 角色编号 */
	public String getRoleId(){
		return roleId;
	}

	/** 角色编号 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	
	/** 角色名称 */
	public String getRoleName(){
		return roleName;
	}

	/** 角色名称 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	
	/** 角色父编号 */
	public String getRolePid(){
		return rolePid;
	}

	/** 角色父编号 */
	public void setRolePid(String rolePid){
		this.rolePid = rolePid;
	}
	
	/** 排序 */
	public Integer getSequ(){
		return sequ;
	}

	/** 排序 */
	public void setSequ(Integer sequ){
		this.sequ = sequ;
	}
	
	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
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
}