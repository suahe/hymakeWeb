package com.hymake.web.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_poster_image 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-06-15 09:19:15
 * @version 1.0
 * @remark create by ca
 */

public class PosterItemEntity {

	/** 图片编号 */
	private long posterItemId;
	/** 类型 */
	private String type;
	/** 名称 */
	private String name;
	/** 媒体文件类型 */
	private String mediaType;
	/** 媒体文件路径 */
	private String path;
	/** 媒体文件内容 */
	private byte[] media;
	/** 链接地址 */
	private String url;
	/** 链接方式 */
	private String isTarget;
	/** 排序 */
	private Integer sequ;
	/** 推送时间 */
	private Date pushTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 类型名称 */
	private String typeName;
	/** 链接方式 */
	private String isTargetName;
	

	/** 项目编号 */
	public long getPosterItemId() {
		return posterItemId;
	}

	/** 项目编号 */
	public void setPosterItemId(long posterItemId) {
		this.posterItemId = posterItemId;
	}
	
	/** 类型 */
	public String getType() {
		return type;
	}

	/** 类型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 名称 */
	public String getName(){
		return name;
	}

	/** 名称 */
	public void setName(String name){
		this.name = name;
	}
	
	/** 媒体文件类型 */
	public String getMediaType() {
		return mediaType;
	}

	/** 媒体文件类型 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	/** 媒体文件路径 */
	public String getPath() {
		return path;
	}

	/** 媒体文件路径 */
	public void setPath(String path) {
		this.path = path;
	}

	/** 媒体文件内容 */
	public byte[] getMedia() {
		return media;
	}

	/** 媒体文件内容 */
	public void setMedia(byte[] media) {
		this.media = media;
	}

	/** 链接地址 */
	public String getUrl(){
		return url;
	}

	/** 链接地址 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/** 链接方式 */
	public String getIsTarget() {
		return isTarget;
	}

	/** 链接方式 */
	public void setIsTarget(String isTarget) {
		this.isTarget = isTarget;
	}

	/** 排序 */
	public Integer getSequ(){
		return sequ;
	}

	/** 排序 */
	public void setSequ(Integer sequ){
		this.sequ = sequ;
	}
	
	/** 推送时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
	public Date getPushTime(){
		return pushTime;
	}

	/** 推送时间 */
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
	
	/** 类型名称 */
	public String getTypeName() {
		return typeName;
	}

	/** 类型名称 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIsTargetName() {
		return isTargetName;
	}

	public void setIsTargetName(String isTargetName) {
		this.isTargetName = isTargetName;
	}

	public PosterItemEntity(){
		
	}
	
	/**
	 * @param posterItemId  项目编号
	 * @param type  类型
	 * @param name  名称
	 * @param mediaType  媒体文件类型
	 * @param path  媒体文件地址
	 * @param url  链接地址
	 * @param target 链接方式
	 * @param sequ  排序
	 * @param pushTime  推送时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public PosterItemEntity(long posterItemId, String type, String name, String mediaType, String path, String url, String isTarget, Integer sequ, Date pushTime, String updateUserid, Date updateTime){
	
		this.posterItemId = posterItemId;
		this.type = type;
		this.name = name;
		this.mediaType = mediaType;
		this.path = path;
		this.url = url;
		this.isTarget = isTarget;
		this.sequ = sequ;
		this.pushTime = pushTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}