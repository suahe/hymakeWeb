package com.dzb.console.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_constant 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-06-26 16:45:46
 * @version 1.0
 * @remark create by ca
 */

public class BaseConstantEntity {

	/** 常量编号 */
	private long constantId;
	/** 常量名称 */
	private String name;
	/** 常量键 */
	private String keyword;
	/** 常量值 */
	private String value;
	/** 备注 */
	private String remark;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 常量编号 */
	public long getConstantId(){
		return constantId;
	}

	/** 常量编号 */
	public void setConstantId(long constantId){
		this.constantId = constantId;
	}
	
	/** 常量名称 */
	public String getName(){
		return name;
	}

	/** 常量名称 */
	public void setName(String name){
		this.name = name;
	}
	
	/** 常量键 */
	public String getKeyword(){
		return keyword;
	}

	/** 常量键 */
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	/** 常量值 */
	public String getValue(){
		return value;
	}

	/** 常量值 */
	public void setValue(String value){
		this.value = value;
	}
	
	/** 备注 */
	public String getRemark(){
		return remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
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
	
	public BaseConstantEntity(){
		
	}
	
	/**
	 * @param constantId  常量编号
	 * @param name  常量名称
	 * @param keyword  常量键
	 * @param value  常量值
	 * @param remark  备注
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseConstantEntity(long constantId, String name, String keyword, String value, String remark, String updateUserid, Date updateTime){
	
		this.constantId = constantId;
		this.name = name;
		this.keyword = keyword;
		this.value = value;
		this.remark = remark;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}