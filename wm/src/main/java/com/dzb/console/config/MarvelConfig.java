package com.dzb.console.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 框架个性化配置
 * @author 38840472@qq.com
 * @date 2020-04-22 16:22:22
 */
@Component
public class MarvelConfig {
	
	/**
	 * 设置记住我天数
	 */
	@Value("${marvel.rememberDays}")
    private Integer rememberDays;

	/**
	 * 设置系统常量缓存的时间（分钟）
	 */
	@Value("${marvel.cache.constant.duration}")
    private Integer cacheConstantDuration;
	
	/**
	 * 设置数据字典缓存的时间（分钟）
	 */
	@Value("${marvel.cache.basetype.duration}")
    private Integer cacheBasetypeDuration;
	
	
	/**
	 * 设置出现验证码的权重值
	 */
	@Value("${marvel.validateCode.maxAttempt}")
    private Integer displayWeight;
	
	/**
	 * 设置用户尝试访问错误次数的值
	 */
	@Value("${marvel.loginAttempt.maxAttempt}")
	private Integer maxAttempt;
	
	/**
	 * 设置用户尝试访问错误的过期时间（分钟）
	 */
	@Value("${marvel.loginAttempt.expireTime}")
	private Integer expireTime;
	
	/**
	 * 用户头像存储位置（只支持本地，相对路径从工程根目录开始）
	 */
	@Value("${marvel.portrait.path}")
	private String portraitPath;
	
	/**
	 * 设置文件的存储模式
	 */
	@Value("${marvel.document.model}")
	private String documentModel;
	
	/**
	 * 设置文件的路径（本地路径）
	 */
	@Value("${marvel.document.loaclPath}")
	private String documentLocalPath;

	/**
	 * 设置文件的路径（本地路径）
	 */
	@Value("${marvel.document.mobileLoaclPath}")
	private String documentMoblieLocalPath;
	
	/**
	 * ftp服务器登录用户名
	 */
	@Value("${marvel.document.ftp.username}")
    private String ftpUsername;
 
	/**
	 * ftp服务器登录密码
	 */
    @Value("${marvel.document.ftp.password}")
    private String ftpPassword;
 
    /**
	 * ftp服务器IP地址
	 */
    @Value("${marvel.document.ftp.host}")
    private String ftpIp;
 
    /**
	 * ftp服务器端口
	 */
    @Value("${marvel.document.ftp.port}")
    private Integer ftpPort;
 
    /**
	 * ftp服务器文件存放根路径
	 */
    @Value("${marvel.document.ftp.serverPath}")
    private String ftpServerPath;

	public Integer getRememberDays() {
		return rememberDays;
	}

	public void setRememberDays(Integer rememberDays) {
		this.rememberDays = rememberDays;
	}

	public int getDisplayWeight() {
		
		return null == displayWeight ? 0 : displayWeight;
	}

	public void setDisplayWeight(Integer displayWeight) {
		this.displayWeight = displayWeight;
	}

	public int getMaxAttempt() {
		return null == maxAttempt ? 0 : maxAttempt;
	}

	public void setMaxAttempt(Integer maxAttempt) {
		this.maxAttempt = maxAttempt;
	}

	public int getExpireTime() {
		return null == expireTime ? 10 : expireTime;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}

	public String getDocumentModel() {
		return documentModel;
	}

	public void setDocumentModel(String documentModel) {
		this.documentModel = documentModel;
	}

	public String getDocumentLocalPath() {
		return documentLocalPath;
	}

	public void setDocumentLocalPath(String documentLocalPath) {
		this.documentLocalPath = documentLocalPath;
	}

	public String getDocumentMoblieLocalPath() {
		return documentMoblieLocalPath;
	}

	public void setDocumentMoblieLocalPath(String documentMoblieLocalPath) {
		this.documentMoblieLocalPath = documentMoblieLocalPath;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public int getFtpPort() {
		return null == ftpPort ? 21 : ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpServerPath() {
		return ftpServerPath;
	}

	public void setFtpServerPath(String ftpServerPath) {
		this.ftpServerPath = ftpServerPath;
	}

	public Integer getCacheConstantDuration() {
		return cacheConstantDuration;
	}

	public void setCacheConstantDuration(Integer cacheConstantDuration) {
		this.cacheConstantDuration = cacheConstantDuration;
	}

	public Integer getCacheBasetypeDuration() {
		return cacheBasetypeDuration;
	}

	public void setCacheBasetypeDuration(Integer cacheBasetypeDuration) {
		this.cacheBasetypeDuration = cacheBasetypeDuration;
	}

	public String getPortraitPath() {
		return portraitPath;
	}

	public void setPortraitPath(String portraitPath) {
		this.portraitPath = portraitPath;
	}
	
}
