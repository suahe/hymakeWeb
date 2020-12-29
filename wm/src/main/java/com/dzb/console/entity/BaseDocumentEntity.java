package com.dzb.console.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_document 表映射实体类
 * @author 38840472@qq.com
 * @date 2020-05-08 14:21:58
 * @version 1.0
 * @remark create by ca
 */

public class BaseDocumentEntity {

	/** 文档编号 */
	private String documentId;
	/** 文件名 */
	private String name;
	/** 扩展名 */
	private String ext;
	/** 文件路径 */
	private String path;
	/** MD5标识 */
	private String md5key;
	/** 文件大小 */
	private Long size;
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
	
	/** 文档编号 */
	public String getDocumentId(){
		return documentId;
	}

	/** 文档编号 */
	public void setDocumentId(String documentId){
		this.documentId = documentId;
	}
	
	/** 文件名 */
	public String getName(){
		return name;
	}

	/** 文件名 */
	public void setName(String name){
		this.name = name;
	}
	
	/** 扩展名 */
	public String getExt(){
		return ext;
	}

	/** 扩展名 */
	public void setExt(String ext){
		this.ext = ext;
	}
	
	/** 文件路径 */
	public String getPath(){
		return path;
	}

	/** 文件路径 */
	public void setPath(String path){
		this.path = path;
	}
	
	/** MD5标识 */
	public String getMd5key(){
		return md5key;
	}

	/** MD5标识 */
	public void setMd5key(String md5key){
		this.md5key = md5key;
	}
	
	/** 文件大小 */
	public Long getSize(){
		return size;
	}

	/** 文件大小 */
	public void setSize(Long size){
		this.size = size;
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
	
	public BaseDocumentEntity(){
		
	}
	
	/**
	 * @param documentId  文档编号
	 * @param name  文件名
	 * @param ext  扩展名
	 * @param path  文件路径
	 * @param md5key  MD5标识
	 * @param size  文件大小
	 * @param isDel  有效状态
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseDocumentEntity(String documentId, String name, String ext, String path, String md5key, Long size, Integer isDel, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.documentId = documentId;
		this.name = name;
		this.ext = ext;
		this.path = path;
		this.md5key = md5key;
		this.size = size;
		this.isDel = isDel;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}