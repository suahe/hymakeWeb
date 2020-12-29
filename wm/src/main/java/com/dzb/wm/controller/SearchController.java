package com.dzb.wm.controller;

import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.SearchEntity;
import com.dzb.wm.service.SearchService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 搜索管理
 */
 
@Controller
@RequestMapping("sc")
public class SearchController {

	private static final Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;
	
	/**
	 * @Description 初始化列表页面
	 */
  	@SysLog(moduleName = "搜索信息管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "wm/t_search_load";
	}
	
	/**
	 * 查询列表
	 */
	@SysLog(moduleName = "搜索信息管理", submoduleName = "查询列表", params = {"offset","limit","searchEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<SearchEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset, @RequestParam(value="limit",required=false,defaultValue="10") int limit, SearchEntity searchEntity) {
		
		return searchService.getPagination(offset, limit ,searchEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 */
	@SysLog(moduleName = "搜索信息管理", submoduleName = "保存新增信息", params = {"searchEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(SearchEntity searchEntity,BindingResult result) {
		
		return searchService.insert(searchEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 */
	@SysLog(moduleName = "搜索信息管理", submoduleName = "通过主键获取信息", params = {"id"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public SearchEntity view(@RequestParam("id") long id,Model model) {
		
		return searchService.getByPkey(id);
	}
	
	/**
	 * @Description 保存编辑信息
	 */
	@SysLog(moduleName = "搜索信息管理", submoduleName = "保存编辑信息", params = {"searchEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(SearchEntity searchEntity,BindingResult result) {
		
		return searchService.update(searchEntity);
	}
	
	/**
	 * @Description 删除信息
	 */
	@SysLog(moduleName = "搜索信息管理", submoduleName = "删除信息", params = {"id"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("id") long id) {
		
		return searchService.delete(id);
	}

}