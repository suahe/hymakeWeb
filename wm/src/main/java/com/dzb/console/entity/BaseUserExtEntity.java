package com.dzb.console.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_base_user_ext 表映射实体类
 * @author 38840472@qq.com
 * @date 2020-04-23 16:20:03
 * @version 1.0
 * @remark create by ca
 */

public class BaseUserExtEntity {

	/** 用户扩展信息编号 */
	private String userExtId;
	/** 用户编号 */
	private String userId;
	/** 性别 */
	private String sex;
	/** 生日 */
	private Date birthday;
	/** 邮箱 */
	private String email;
	/** 移动电话 */
	private String mobile;
	/** 固定电话 */
	private String tel;
	/** 创建人 */
	private String createUserid;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	/** 错误尝试次数 */
	private int attempts;
	/** 是否被锁定 */
	private boolean isBlocked;
	/** 性别 */
	private String sexName;
	
	/** 用户扩展信息编号 */
	public String getUserExtId(){
		return userExtId;
	}

	/** 用户扩展信息编号 */
	public void setUserExtId(String userExtId){
		this.userExtId = userExtId;
	}
	
	/** 用户编号 */
	public String getUserId(){
		return userId;
	}

	/** 用户编号 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/** 性别 */
	public String getSex(){
		return sex;
	}

	/** 性别 */
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getBirthday(){
		return birthday;
	}

	/** 生日 */
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	/** 邮箱 */
	public String getEmail(){
		return email;
	}

	/** 邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/** 移动电话 */
	public String getMobile(){
		return mobile;
	}

	/** 移动电话 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	/** 固定电话 */
	public String getTel(){
		return tel;
	}

	/** 固定电话 */
	public void setTel(String tel){
		this.tel = tel;
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
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

	/** 错误尝试次数 */
	public int getAttempts() {
		return attempts;
	}

	/** 错误尝试次数 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	/** 是否被锁定 */
	public boolean isBlocked() {
		return isBlocked;
	}

	/** 是否被锁定 */
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public BaseUserExtEntity(){
		
	}
	
	/**
	 * @param userExtId  用户扩展信息编号
	 * @param userId  用户编号
	 * @param sex  性别
	 * @param birthday  生日
	 * @param email  邮箱
	 * @param mobile  移动电话
	 * @param tel  固定电话
	 * @param attempts 错误尝试次数
	 * @param isBlocked 是否被锁定
	 * @param createUserid  创建人
	 * @param createTime  创建时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public BaseUserExtEntity(String userExtId, String userId, String sex, Date birthday, String email, String mobile, String tel, int attempts, boolean isBlocked, String createUserid, Date createTime, String updateUserid, Date updateTime){
	
		this.userExtId = userExtId;
		this.userId = userId;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.mobile = mobile;
		this.tel = tel;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
		this.attempts = attempts;
		this.isBlocked = isBlocked;
	}
}