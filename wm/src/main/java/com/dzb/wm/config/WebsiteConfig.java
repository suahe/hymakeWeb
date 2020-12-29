package com.dzb.wm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 网站配置信息
 * @author 38840472@qq.com
 * @date 2020-06-05 14:29:19
 */
@Component
public class WebsiteConfig {
	
	/**
	 * 网站地址
	 */
	@Value("${website.url}")
    private String url;

	/**
	 * 全局网站名称
	 */
	@Value("${website.global.name}")
    private String globalName;
	
	/**
	 * 全局keyword
	 */
	@Value("${website.global.keyword}")
    private String globalKeyword;
	
	/**
	 * 全局slogan
	 */
	@Value("${website.global.slogan}")
    private String globalSlogan;
	
	/**
	 * 网站新闻列表路径
	 */
	@Value("${website.path.news.list}")
    private String pathNewsList;
	
	/**
	 * 网站新闻详细信息路径
	 */
	@Value("${website.path.news.view}")
    private String pathNewsView;
	
	/**
	 * 网站新闻图片路径
	 */
	@Value("${website.path.news.image}")
    private String pathNewsImage;
	
	/**
	 * 图片存放路径
	 */
	@Value("${website.path.image}")
    private String pathImage;
	
	/**
	 * 视频存放路径
	 */
	@Value("${website.path.video}")
    private String pathVideo;
	
	/**
	 * 主页文件路径
	 */
	@Value("${website.path.index}")
    private String index;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGlobalName() {
		return globalName;
	}

	public void setGlobalName(String globalName) {
		this.globalName = globalName;
	}

	public String getGlobalKeyword() {
		return globalKeyword;
	}

	public void setGlobalKeyword(String globalKeyword) {
		this.globalKeyword = globalKeyword;
	}

	public String getGlobalSlogan() {
		return globalSlogan;
	}

	public void setGlobalSlogan(String globalSlogan) {
		this.globalSlogan = globalSlogan;
	}

	public String getPathNewsList() {
		return pathNewsList;
	}

	public void setPathNewsList(String pathNewsList) {
		this.pathNewsList = pathNewsList;
	}

	public String getPathNewsView() {
		return pathNewsView;
	}

	public void setPathNewsView(String pathNewsView) {
		this.pathNewsView = pathNewsView;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getPathVideo() {
		return pathVideo;
	}

	public void setPathVideo(String pathVideo) {
		this.pathVideo = pathVideo;
	}

	public String getPathNewsImage() {
		return pathNewsImage;
	}

	public void setPathNewsImage(String pathNewsImage) {
		this.pathNewsImage = pathNewsImage;
	}
	
}
