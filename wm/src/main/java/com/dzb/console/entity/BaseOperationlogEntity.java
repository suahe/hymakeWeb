package com.dzb.console.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/***
 * @Description t_base_operationlog 表映射实体类
 * @author 38840472@qq.com
 * @date 2019-01-10 14:47:58
 * @version 1.0
 * @remark create by ca
 */

public class BaseOperationlogEntity {

	/** 操作日志信息编号 */
	private long operationlogId;
	/** 日志时间 */
	private java.util.Date logTime;
	/** 用户编号 */
	private String userId;
	/** 用户日志编号 */
	private String userlogId;
	/** 模块名称 */
	private String moduleName;
	/** 子模块名称 */
	private String submoduleName;
	/** 类名 */
	private String className;
	/** 方法名 */
	private String methodName;
	/** 参数 */
	private String params;
	/** 用户名称 */
	private String username;
	
	/** 操作日志信息编号 */
	public long getOperationlogId(){
		return operationlogId;
	}

	/** 操作日志信息编号 */
	public void setOperationlogId(long operationlogId){
		this.operationlogId = operationlogId;
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
	
	/** 用户日志编号 */
	public String getUserlogId(){
		return userlogId;
	}

	/** 用户日志编号 */
	public void setUserlogId(String userlogId){
		this.userlogId = userlogId;
	}
	
	/** 模块名称 */
	public String getModuleName(){
		return moduleName;
	}

	/** 模块名称 */
	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	
	/** 子模块名称 */
	public String getSubmoduleName(){
		return submoduleName;
	}

	/** 子模块名称 */
	public void setSubmoduleName(String submoduleName){
		this.submoduleName = submoduleName;
	}
	
	/** 类名 */
	public String getClassName(){
		return className;
	}

	/** 类名 */
	public void setClassName(String className){
		this.className = className;
	}
	
	/** 方法名 */
	public String getMethodName(){
		return methodName;
	}

	/** 方法名 */
	public void setMethodName(String methodName){
		this.methodName = methodName;
	}
	
	/** 参数 */
	public String getParams(){
		return params;
	}

	/** 参数 */
	public void setParams(String params){
		this.params = params;
	}

	/** 用户名称 */
	public String getUsername() {
		return username;
	}

	/** 用户名称 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}