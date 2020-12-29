package com.hymake.mobileWeb.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_type_list 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-05-13 14:59:08
 * @version 1.0
 * @remark create by ca
 */

public class BaseTypeListEntity {

	/** 列表编号 */
	private String baseTypeListId;
	/** 数据字典编号 */
	private String baseTypeId;
	/** 名称 */
	private String name;
	/** 代码 */
	private String value;
	/** 排序 */
	private Integer sequ;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 列表编号 */
	public String getBaseTypeListId(){
		return baseTypeListId;
	}

	/** 列表编号 */
	public void setBaseTypeListId(String baseTypeListId){
		this.baseTypeListId = baseTypeListId;
	}
	
	/** 数据字典编号 */
	public String getBaseTypeId(){
		return baseTypeId;
	}

	/** 数据字典编号 */
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
	public String getValue(){
		return value;
	}

	/** 代码 */
	public void setValue(String value){
		this.value = value;
	}
	
	/** 排序 */
	public Integer getSequ(){
		return sequ;
	}

	/** 排序 */
	public void setSequ(Integer sequ){
		this.sequ = sequ;
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
	
	public BaseTypeListEntity(){
		
	}
	
	/**
	 * @param baseTypeListId  列表编号
	 * @param baseTypeId  数据字典编号
	 * @param name  名称
	 * @param value  代码
	 * @param sequ  排序
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseTypeListEntity(String baseTypeListId, String baseTypeId, String name, String value, Integer sequ, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.baseTypeListId = baseTypeListId;
		this.baseTypeId = baseTypeId;
		this.name = name;
		this.value = value;
		this.sequ = sequ;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}