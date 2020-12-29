package com.dzb.console.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_department 表映射实体类
 * @author 38840472@qq.com
 * @date 2020-05-26 23:22:37
 * @version 1.0
 * @remark create by ca
 */

public class BaseDepartmentEntity {

	/** 部门信息编号 */
	private String departmentId;
	/** 部门名称 */
	private String departmentName;
	/** 部门信息父编号 */
	private String departmentPid;
	/** 备注 */
	private String remark;
	/** 排序 */
	private Integer sequ;
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
	
	/** 部门信息编号 */
	public String getDepartmentId(){
		return departmentId;
	}

	/** 部门信息编号 */
	public void setDepartmentId(String departmentId){
		this.departmentId = departmentId;
	}
	
	/** 部门名称 */
	public String getDepartmentName(){
		return departmentName;
	}

	/** 部门名称 */
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	
	/** 部门信息父编号 */
	public String getDepartmentPid(){
		return departmentPid;
	}

	/** 部门信息父编号 */
	public void setDepartmentPid(String departmentPid){
		this.departmentPid = departmentPid;
	}
	
	/** 备注 */
	public String getRemark(){
		return remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/** 排序 */
	public Integer getSequ() {
		return sequ;
	}

	/** 排序 */
	public void setSequ(Integer sequ) {
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
	
	public BaseDepartmentEntity(){
		
	}
	
	/**
	 * @param departmentId  部门信息编号
	 * @param departmentName  部门名称
	 * @param departmentPid  部门信息父编号
	 * @param remark  备注
	 * @param sequ 排序
	 * @param isDel  有效状态
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseDepartmentEntity(String departmentId, String departmentName, String departmentPid, String remark, Integer sequ, Integer isDel, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentPid = departmentPid;
		this.remark = remark;
		this.sequ = sequ;
		this.isDel = isDel;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}