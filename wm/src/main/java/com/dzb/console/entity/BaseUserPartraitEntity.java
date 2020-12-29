package com.dzb.console.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_user_partrait 表映射实体类
 * @author 38840472@qq.com
 * @date 2020-04-23 21:13:27
 * @version 1.0
 * @remark create by ca
 */

public class BaseUserPartraitEntity {

	/** 用户头像编号 */
	private long userPortraitId;
	/** 用户编号 */
	private String userId;
	/** 文件类型 */
	private String type;
	/** 文件类型 */
	private String mediaType;
	/** 图像地址 */
	private String path;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 用户头像编号 */
	public long getUserPortraitId(){
		return userPortraitId;
	}

	/** 用户头像编号 */
	public void setUserPortraitId(long userPortraitId){
		this.userPortraitId = userPortraitId;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 文件类型 */
	public String getType() {
		return type;
	}

	/** 文件类型 */
	public void setType(String type) {
		this.type = type;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/** 图像内容 */
	public String getPath(){
		return path;
	}

	/** 图像内容 */
	public void setPath(String path){
		this.path = path;
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
	
	public BaseUserPartraitEntity(){
		
	}
	
	/**
	 * @param userPortraitId  用户头像编号
	 * @param userId  用户编号
	 * @param mediaType 文件类型
	 * @param path  图像地址
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseUserPartraitEntity(long userPortraitId, String userId, String mediaType, String path, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.userPortraitId = userPortraitId;
		this.userId = userId;
		this.mediaType = mediaType;
		this.path = path;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
	
}