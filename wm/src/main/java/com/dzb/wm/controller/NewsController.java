package com.dzb.wm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.MediaFileEntity;
import com.dzb.console.syslog.SysLog;
import com.dzb.wm.config.WebsiteConfig;
import com.dzb.wm.entity.NewsEntity;
import com.dzb.wm.service.NewsService;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_news 表Controller类
 * @author daizb@hymake.com
 * @date 2020-06-03 13:39:45
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("nc")
public class NewsController{

	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;
	@Autowired
	private WebsiteConfig websiteConfig;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
  	@SysLog(moduleName = "新闻资讯管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		log.debug("初始化列表页面");
		
		model.addAttribute("websiteUrl", websiteConfig.getUrl());
		return "wm/t_news_load";
	}
  	
  	@SysLog(moduleName = "新闻资讯管理", submoduleName = "初始化新增页面", params = {})
	@RequestMapping(value = "/loadAdd",method = RequestMethod.GET)
	public String loadAdd() {
		
		log.debug("初始化列表页面");
		return "wm/t_news_add";
	}
  	
  	@SysLog(moduleName = "新闻资讯管理", submoduleName = "初始化编辑页面", params = {"newsId"})
	@RequestMapping(value = "/loadEdit",method = RequestMethod.GET)
	public String loadEdit(@RequestParam("newsId") long newsId, Model model) {
		
		log.debug("初始化列表页面");
		model.addAttribute("websiteUrl", websiteConfig.getUrl());
		NewsEntity newsEntity = newsService.getByPkey(newsId);
		model.addAttribute("newsEntity", newsEntity);
		return "wm/t_news_edit";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "查询列表", params = {"offset","limit","newsEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<NewsEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,NewsEntity newsEntity, Model model) {
		
		return newsService.getPagination(offset, limit ,newsEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param newsEntity
	 * @param image
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-23 17:28:12
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "保存新增信息", params = {})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(NewsEntity newsEntity, @RequestParam("image") String image, BindingResult result) {
		
		return newsService.insert(newsEntity, image);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param newsId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "通过主键获取信息", params = {"newsId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public NewsEntity view(@RequestParam("newsId") long newsId,Model model) {
		
		return newsService.getByPkey(newsId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param newsEntity
	 * @param image
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-23 17:28:02
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "保存编辑信息", params = {})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(NewsEntity newsEntity, @RequestParam("image") String image, BindingResult result) {
		
		return newsService.update(newsEntity, image);
	}
	
	/**
	 * @Description 删除信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "删除信息", params = {"newsId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("newsId") long newsId) {
		
		return newsService.delete(newsId);
	}
	
	/**
	 * @Description 发布新闻资讯
	 * @param newsId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:10:18
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "发布新闻资讯", params = {"newsId", "pushTime"})
	@RequestMapping(value = "/push",method = RequestMethod.POST)
	@ResponseBody
	public int push(@RequestParam("newsId") long newsId, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")@RequestParam("pushTime") Date pushTime) {
		
		return newsService.updatePushTimeByNewsId(newsId, pushTime);
	}
	
	/**
	 * @Description 下架新闻资讯
	 * @param newsId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:10:18
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "下架新闻资讯", params = {"newsId"})
	@RequestMapping(value = "/pull",method = RequestMethod.POST)
	@ResponseBody
	public int pull(@RequestParam("newsId") long newsId) {
		
		return newsService.updatePushTimeByNewsId(newsId, null);
	}
	
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "设置为头条新闻", params = {"newsId"})
	@RequestMapping(value = "/confirmHeadline",method = RequestMethod.POST)
	@ResponseBody
	public int confirmHeadline(@RequestParam("newsId") long newsId) {
		
		return newsService.updateIsHeadlineByNewsId(newsId, 1);
	}
	
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "取消头条新闻", params = {"newsId"})
	@RequestMapping(value = "/cancelHeadline",method = RequestMethod.POST)
	@ResponseBody
	public int cancelHeadline(@RequestParam("newsId") long newsId) {
		
		return newsService.updateIsHeadlineByNewsId(newsId, 0);
	}
	
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "查询头条的数量", params = {})
	@RequestMapping(value = "/countByIsHeadline",method = RequestMethod.GET)
	@ResponseBody
	public int countByIsHeadline() {
		
		return newsService.countByIsHeadline();
	}
	
	@SysLog(moduleName = "公告项目管理", submoduleName = "获取图片信息", params = {"newsId"})
	@GetMapping(value= "/loadImage")
    @ResponseBody
    public ResponseEntity<byte[]> loadImage(@RequestParam("newsId") long newsId) {
        
    	MediaFileEntity mediaFileEntity = newsService.getHeadLineImage(newsId);
		if(null == mediaFileEntity) {
			return null;
		}else {
			final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaFileEntity.getMediaType());
	        return new ResponseEntity<>(mediaFileEntity.getMedia(), headers, HttpStatus.OK);
		}
    }
	
}