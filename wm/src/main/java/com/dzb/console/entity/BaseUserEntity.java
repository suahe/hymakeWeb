package com.dzb.console.entity;
/***
 * @Description t_base_user 表映射实体类
 * @author 38840472@qq.com
 * @date 2018-12-18 16:24:42
 * @version 1.0
 * @remark create by ca
 */

public class BaseUserEntity {

	/** 用户编号 */
	private String userId;
	/** 账号 */
	private String username;
	/** 密码 */
	private String password;
	/** 用户名 */
	private String name;
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
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 账号 */
	public String getUsername(){
		return username;
	}

	/** 账号 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/** 密码 */
	public String getPassword(){
		return password;
	}

	/** 密码 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/** 用户名 */
	public String getName(){
		return name;
	}

	/** 用户名 */
	public void setName(String name){
		this.name = name;
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