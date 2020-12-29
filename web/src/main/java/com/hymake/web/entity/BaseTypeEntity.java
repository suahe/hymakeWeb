package com.hymake.web.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_type 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-05-12 17:51:09
 * @version 1.0
 * @remark create by ca
 */

public class BaseTypeEntity {

	/** 编号 */
	private String baseTypeId;
	/** 名称 */
	private String name;
	/** 代码 */
	private String code;
	/** 类型（1：列表，2：树） */
	private Integer type;
	/** 版本 */
	private Integer ver;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 编号 */
	public String getBaseTypeId(){
		return baseTypeId;
	}

	/** 编号 */
	public void setBaseTypeId(String baseTypeId){
		this.baseTypeId = baseTypeId;
	}
	
	/** 名称 */
	public String getName(){
		return name;
	}

	/** 名称 */
	public void setName(String name){
		this.name = name;
	}
	
	/** 代码 */
	public String getCode(){
		return code;
	}

	/** 代码 */
	public void setCode(String code){
		this.code = code;
	}
	
	/** 类型（1：列表，2：树） */
	public Integer getType(){
		return type;
	}

	/** 类型（1：列表，2：树） */
	public void setType(Integer type){
		this.type = type;
	}
	
	/** 版本 */
	public Integer getVer(){
		return ver;
	}

	/** 版本 */
	public void setVer(Integer ver){
		this.ver = ver;
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
	
	public BaseTypeEntity(){
		
	}
	
	/**
	 * @param baseTypeId  编号
	 * @param name  名称
	 * @param code  代码
	 * @param type  类型（1：列表，2：树）
	 * @param ver  版本
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseTypeEntity(String baseTypeId, String name, String code, Integer type, Integer ver, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.baseTypeId = baseTypeId;
		this.name = name;
		this.code = code;
		this.type = type;
		this.ver = ver;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}