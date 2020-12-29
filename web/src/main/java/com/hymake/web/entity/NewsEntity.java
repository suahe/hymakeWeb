package com.hymake.web.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description t_news 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-06-23 16:04:02
 * @version 1.0
 * @remark create by ca
 */

public class NewsEntity {

	/** 新闻资讯编号 */
	private long newsId;
	/** 是否头条新闻 (1：头条新闻，0：不是头条新闻)*/
	private int isHeadline;
	/** 媒体文件类型 */
	private String mediaType;
	/** 扩展名 */
	private String ext;
	/** 媒体文件地址 */
	private String path;
	/** 新闻类型 */
	private String newType;
	/** 关键词 */
	private String keywords;
	/** 新闻资讯标题 */
	private String title;
	/** 新闻资讯摘要 */
	private String overview;
	/** 新闻资讯内容 */
	private String content;
	/** 外部链接 */
	private String url;
	/** 发布者 */
	private String author;
	/** 发布时间 */
	private Date pushTime;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** -------数据字典------- **/
	
	/** 新闻类型 */
	private String newTypeName;
	/** 发布者 */
	private String authorName;
	
	/** -------数据字典------- **/
	
	/** 新闻资讯编号 */
	public long getNewsId(){
		return newsId;
	}

	/** 新闻资讯编号 */
	public void setNewsId(long newsId){
		this.newsId = newsId;
	}
	
	/** 是否头条新闻 */
	public int getIsHeadline(){
		return isHeadline;
	}

	/** 是否头条新闻 */
	public void setIsHeadline(int isHeadline){
		this.isHeadline = isHeadline;
	}
	
	/** 媒体文件类型 */
	public String getMediaType(){
		return mediaType;
	}

	/** 媒体文件类型 */
	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}
	
	/** 扩展名 */
	public String getExt(){
		return ext;
	}

	/** 扩展名 */
	public void setExt(String ext){
		this.ext = ext;
	}
	
	/** 媒体文件地址 */
	public String getPath(){
		return path;
	}

	/** 媒体文件地址 */
	public void setPath(String path){
		this.path = path;
	}
	
	/** 新闻类型 */
	public String getNewType(){
		return newType;
	}

	/** 新闻类型 */
	public void setNewType(String newType){
		this.newType = newType;
	}
	
	/** 关键词 */
	public String getKeywords(){
		return keywords;
	}

	/** 关键词 */
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	
	/** 新闻资讯标题 */
	public String getTitle(){
		return title;
	}

	/** 新闻资讯标题 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/** 新闻资讯摘要 */
	public String getOverview(){
		return overview;
	}

	/** 新闻资讯摘要 */
	public void setOverview(String overview){
		this.overview = overview;
	}
	
	/** 新闻资讯内容 */
	public String getContent(){
		return content;
	}

	/** 新闻资讯内容 */
	public void setContent(String content){
		this.content = content;
	}
	
	/** 外部链接 */
	public String getUrl(){
		return url;
	}

	/** 外部链接 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/** 发布者 */
	public String getAuthor(){
		return author;
	}

	/** 发布者 */
	public void setAuthor(String author){
		this.author = author;
	}
	
	/** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getPushTime(){
		return pushTime;
	}

	/** 发布时间 */
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
	
	public String getNewTypeName() {
		return newTypeName;
	}

	public void setNewTypeName(String newTypeName) {
		this.newTypeName = newTypeName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public NewsEntity(){
		
	}
	
	/**
	 * @param newsId  新闻资讯编号
	 * @param isHeadline  是否头条新闻
	 * @param mediaType  媒体文件类型
	 * @param ext  扩展名
	 * @param path  媒体文件地址
	 * @param newType  新闻类型
	 * @param keywords  关键词
	 * @param title  新闻资讯标题
	 * @param overview  新闻资讯摘要
	 * @param content  新闻资讯内容
	 * @param url  外部链接
	 * @param author  发布者
	 * @param pushTime  发布时间
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public NewsEntity(long newsId, int isHeadline, String mediaType, String ext, String path, String newType, String keywords, String title, String overview, String content, String url, String author, Date pushTime, String updateUserid, Date updateTime){
	
		this.newsId = newsId;
		this.isHeadline = isHeadline;
		this.mediaType = mediaType;
		this.ext = ext;
		this.path = path;
		this.newType = newType;
		this.keywords = keywords;
		this.title = title;
		this.overview = overview;
		this.content = content;
		this.url = url;
		this.author = author;
		this.pushTime = pushTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}