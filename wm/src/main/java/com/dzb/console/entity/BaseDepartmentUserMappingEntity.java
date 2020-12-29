package com.dzb.console.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_department_user_mapping 表映射实体类
 * @author 38840472@qq.com
 * @date 2020-05-27 00:34:45
 * @version 1.0
 * @remark create by ca
 */

public class BaseDepartmentUserMappingEntity {

	/** 部门人员关系编号 */
	private String departmentUserId;
	/** 用户编号 */
	private String userId;
	/** 部门信息编号 */
	private String departmentId;
	/** 是否为负责人 */
	private Integer isLeader;
	/** 有效状态 */
	private Integer isDel;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 用户名称 */
	private String name;
	/** 账户 */
	private String username;
	
	/** 部门人员关系编号 */
	public String getDepartmentUserId(){
		return departmentUserId;
	}

	/** 部门人员关系编号 */
	public void setDepartmentUserId(String departmentUserId){
		this.departmentUserId = departmentUserId;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 部门信息编号 */
	public String getDepartmentId(){
		return departmentId;
	}

	/** 部门信息编号 */
	public void setDepartmentId(String departmentId){
		this.departmentId = departmentId;
	}
	
	/** 是否为负责人 */
	public Integer getIsLeader(){
		return isLeader;
	}

	/** 是否为负责人 */
	public void setIsLeader(Integer isLeader){
		this.isLeader = isLeader;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateTime(){
		return createTime;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime){
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getUpdateTime(){
		return updateTime;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
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
	
	
	public BaseDepartmentUserMappingEntity(){
		
	}
	
	/**
	 * @param departmentUserId  部门人员关系编号
	 * @param userId  用户编号
	 * @param departmentId  部门信息编号
	 * @param isLeader  是否为负责人
	 * @param username  账户
	 * @param name  用户姓名
	 * @param isDel  有效状态
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseDepartmentUserMappingEntity(String departmentUserId, String userId, String departmentId, Integer isLeader, String username, String name, Integer isDel, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.departmentUserId = departmentUserId;
		this.userId = userId;
		this.departmentId = departmentId;
		this.isLeader = isLeader;
		this.username = username;
		this.name = name;
		this.isDel = isDel;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}