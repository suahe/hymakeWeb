package com.dzb.console.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/***
 * @Description t_base_userlog 表映射实体类
 * @author 38840472@qq.com
 * @date 2019-01-03 10:48:20
 * @version 1.0
 * @remark create by ca
 */

public class BaseUserlogEntity {

	/** 日志信息编号 */
	private long userlogId;
	/** 日志时间 */
	private java.util.Date logTime;
	/** 用户编号 */
	private String userId;
	/** 网络地址 */
	private String remoteAddress;
	/** 日志类型 */
	private Integer logType;
	/** 会话编号 */
	private String sessionid;
	/** 账号 */
	private String username;
	/** 用户名 */
	private String name;
	/** 错误消息 */
	private String errorMessage;
	
	/** 日志信息编号 */
	public long getUserlogId(){
		return userlogId;
	}

	/** 日志信息编号 */
	public void setUserlogId(long userlogId){
		this.userlogId = userlogId;
	}
	
	/** 日志时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public java.util.Date getLogTime(){
		return logTime;
	}

	/** 日志时间 */
	public void setLogTime(java.util.Date logTime){
		this.logTime = logTime;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 网络地址 */
	public String getRemoteAddress(){
		return remoteAddress;
	}

	/** 网络地址 */
	public void setRemoteAddress(String remoteAddress){
		this.remoteAddress = remoteAddress;
	}
	
	/** 日志类型 */
	public Integer getLogType(){
		return logType;
	}

	/** 日志类型 */
	public void setLogType(Integer logType){
		this.logType = logType;
	}
	
	/** 会话编号 */
	public String getSessionid(){
		return sessionid;
	}

	/** 会话编号 */
	public void setSessionid(String sessionid){
		this.sessionid = sessionid;
	}

	/** 用户名 */
	public String getName() {
		return name;
	}

	/** 用户名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 账号 */
	public String getUsername() {
		return username;
	}
	
	/** 账号 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 错误消息 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/** 错误消息 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}