package com.dzb.console.entity;
/***
 * @Description t_base_retrieve_password 表映射实体类
 * @author 38840472@qq.com
 * @date 2019-01-16 11:04:43
 * @version 1.0
 * @remark create by ca
 */

public class BaseRetrievePasswordEntity {

	/** 找回密码编号 */
	private String retrievePasswordId;
	/** 用户编号 */
	private String userId;
	/** 验证码生成时间 */
	private java.util.Date securityCodeCreateTime;
	/** 验证码 */
	private String securityCode;
	/** 网络地址 */
	private String remoteAddress;
	/** 所在地区 */
	private String area;
	/** 验证码使用时间 */
	private java.util.Date securityCodeUseTime;
	/** 账户 */
	private String username;
	
	/** 找回密码编号 */
	public String getRetrievePasswordId(){
		return retrievePasswordId;
	}

	/** 找回密码编号 */
	public void setRetrievePasswordId(String retrievePasswordId){
		this.retrievePasswordId = retrievePasswordId;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 验证码生成时间 */
	public java.util.Date getSecurityCodeCreateTime(){
		return securityCodeCreateTime;
	}

	/** 验证码生成时间 */
	public void setSecurityCodeCreateTime(java.util.Date securityCodeCreateTime){
		this.securityCodeCreateTime = securityCodeCreateTime;
	}
	
	/** 验证码 */
	public String getSecurityCode(){
		return securityCode;
	}

	/** 验证码 */
	public void setSecurityCode(String securityCode){
		this.securityCode = securityCode;
	}
	
	/** 网络地址 */
	public String getRemoteAddress(){
		return remoteAddress;
	}

	/** 网络地址 */
	public void setRemoteAddress(String remoteAddress){
		this.remoteAddress = remoteAddress;
	}
	
	/** 所在地区 */
	public String getArea(){
		return area;
	}

	/** 所在地区 */
	public void setArea(String area){
		this.area = area;
	}
	
	/** 验证码使用时间 */
	public java.util.Date getSecurityCodeUseTime(){
		return securityCodeUseTime;
	}

	/** 验证码使用时间 */
	public void setSecurityCodeUseTime(java.util.Date securityCodeUseTime){
		this.securityCodeUseTime = securityCodeUseTime;
	}

	/** 账户 */
	public String getUsername() {
		return username;
	}

	/** 账户 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}