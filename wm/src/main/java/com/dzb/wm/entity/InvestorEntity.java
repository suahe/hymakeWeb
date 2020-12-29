package com.dzb.wm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_investor 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-08-11 19:02:47
 * @version 1.0
 * @remark create by ca
 */

public class InvestorEntity {

	/** 投资者关系信息编号 */
	private long investorId;
	/** 公告类型 */
	private String type;
	/** 标题 */
	private String title;
	/** 文档链接 */
	private String path;
	/** 发布时间 */
	private Date pushTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	/** 公告类型 */
	private String typeName;
	
	private Date pushTimeStart;
	
	private Date pushTimeEnd;
	
	/** 投资者关系信息编号 */
	public long getInvestorId(){
		return investorId;
	}

	/** 投资者关系信息编号 */
	public void setInvestorId(long investorId){
		this.investorId = investorId;
	}
	
	/** 公告类型 */
	public String getType(){
		return type;
	}

	/** 公告类型 */
	public void setType(String type){
		this.type = type;
	}
	
	/** 标题 */
	public String getTitle(){
		return title;
	}

	/** 标题 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/** 文档链接 */
	public String getPath(){
		return path;
	}

	/** 文档链接 */
	public void setPath(String path){
		this.path = path;
	}
	
	/** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
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
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getPushTimeStart() {
		return pushTimeStart;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setPushTimeStart(Date pushTimeStart) {
		this.pushTimeStart = pushTimeStart;
	}

	public Date getPushTimeEnd() {
		return pushTimeEnd;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setPushTimeEnd(Date pushTimeEnd) {
		this.pushTimeEnd = pushTimeEnd;
	}

	public InvestorEntity(){
		
	}
	
	/**
	 * @param investorId  投资者关系信息编号
	 * @param type  公告类型
	 * @param title  标题
	 * @param path  文档链接
	 * @param pushTime  发布时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public InvestorEntity(long investorId, String type, String title, String path, Date pushTime, String updateUserid, Date updateTime){
	
		this.investorId = investorId;
		this.type = type;
		this.title = title;
		this.path = path;
		this.pushTime = pushTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}