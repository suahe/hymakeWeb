package com.dzb.wm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_join 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */

public class JoinEntity {

	/** 招聘编号 */
	private long joinId;
	/** 招聘类型 */
	private String joinType;
	/** 招聘人数 */
	private Integer recruitmentNumber;
	/** 工作地点 */
	private String workplace;
	/** 截止日期 */
	private Date expirationDate;
	/** 岗位名称 */
	private String postName;
	/** 岗位要求 */
	private String jobRequirements;
	/** 加入方式 */
	private String joinWay;
	/** 发布时间 */
	private Date pushTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 招聘类型 */
	private String joinTypeName;
	/** 工作地点 */
	private String workplaceName;
	
	/** 招聘编号 */
	public long getJoinId(){
		return joinId;
	}

	/** 招聘编号 */
	public void setJoinId(long joinId){
		this.joinId = joinId;
	}
	
	/** 招聘类型 */
	public String getJoinType(){
		return joinType;
	}

	/** 招聘类型 */
	public void setJoinType(String joinType){
		this.joinType = joinType;
	}
	
	/** 招聘人数 */
	public Integer getRecruitmentNumber(){
		return recruitmentNumber;
	}

	/** 招聘人数 */
	public void setRecruitmentNumber(Integer recruitmentNumber){
		this.recruitmentNumber = recruitmentNumber;
	}
	
	/** 工作地点 */
	public String getWorkplace(){
		return workplace;
	}

	/** 工作地点 */
	public void setWorkplace(String workplace){
		this.workplace = workplace;
	}
	
	/** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getExpirationDate(){
		return expirationDate;
	}

	/** 截止日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
	public void setExpirationDate(Date expirationDate){
		this.expirationDate = expirationDate;
	}
	
	/** 岗位名称 */
	public String getPostName(){
		return postName;
	}

	/** 岗位名称 */
	public void setPostName(String postName){
		this.postName = postName;
	}
	
	/** 岗位要求 */
	public String getJobRequirements(){
		return jobRequirements;
	}

	/** 岗位要求 */
	public void setJobRequirements(String jobRequirements){
		this.jobRequirements = jobRequirements;
	}
	
	/** 加入方式 */
	public String getJoinWay(){
		return joinWay;
	}

	/** 加入方式 */
	public void setJoinWay(String joinWay){
		this.joinWay = joinWay;
	}
	
	/** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getPushTime(){
		return pushTime;
	}

	/** 发布时间 */
	public void setPushTime(Date pushTime){
		this.pushTime = pushTime;
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
	
	public String getJoinTypeName() {
		return joinTypeName;
	}

	public void setJoinTypeName(String joinTypeName) {
		this.joinTypeName = joinTypeName;
	}

	public String getWorkplaceName() {
		return workplaceName;
	}

	public void setWorkplaceName(String workplaceName) {
		this.workplaceName = workplaceName;
	}

	public JoinEntity(){
		
	}
	
	/**
	 * @param joinId  招聘编号
	 * @param joinType  招聘类型
	 * @param recruitmentNumber  招聘人数
	 * @param workplace  工作地点
	 * @param expirationDate  截止日期
	 * @param postName  岗位名称
	 * @param jobRequirements  岗位要求
	 * @param joinWay  加入方式
	 * @param pushTime  发布时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public JoinEntity(long joinId, String joinType, Integer recruitmentNumber, String workplace, Date expirationDate, String postName, String jobRequirements, String joinWay, Date pushTime, String updateUserid, Date updateTime){
	
		this.joinId = joinId;
		this.joinType = joinType;
		this.recruitmentNumber = recruitmentNumber;
		this.workplace = workplace;
		this.expirationDate = expirationDate;
		this.postName = postName;
		this.jobRequirements = jobRequirements;
		this.joinWay = joinWay;
		this.pushTime = pushTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}