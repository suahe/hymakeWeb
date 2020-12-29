package com.dzb.console.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.dzb.console.entity.BaseConstantEntity;
import com.dzb.console.service.BaseConstantService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description 系统常量管理表Controller类
 * @author daizb@hymake.com
 * @date 2020-06-26 16:45:46
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bcc")
public class BaseConstantController{

	private static final Logger log = LoggerFactory.getLogger(BaseConstantController.class);

	@Autowired
	private BaseConstantService baseConstantService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
  	@SysLog(moduleName = "系统常量管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {

		log.debug("初始化列表页面");
		return "console/t_base_constant_load";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	@SysLog(moduleName = "系统常量管理", submoduleName = "查询列表", params = {"offset","limit","baseConstantEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseConstantEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,BaseConstantEntity baseConstantEntity, Model model) {
		
		return baseConstantService.getPagination(offset, limit ,baseConstantEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseConstantEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	@SysLog(moduleName = "系统常量管理", submoduleName = "保存新增信息", params = {"baseConstantEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(BaseConstantEntity baseConstantEntity,BindingResult result) {
		
		return baseConstantService.insert(baseConstantEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param constantId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	@SysLog(moduleName = "系统常量管理", submoduleName = "通过主键获取信息", params = {"constantId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public BaseConstantEntity view(@RequestParam("constantId") long constantId,Model model) {
		
		return baseConstantService.getByPkey(constantId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param baseConstantEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	@SysLog(moduleName = "系统常量管理", submoduleName = "保存编辑信息", params = {"baseConstantEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(BaseConstantEntity baseConstantEntity,BindingResult result) {
		
		return baseConstantService.update(baseConstantEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param constantId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	@SysLog(moduleName = "系统常量管理", submoduleName = "删除信息", params = {"constantId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("constantId") long constantId) {
		
		return baseConstantService.delete(constantId);
	}
	
	@SysLog(moduleName = "系统常量管理", submoduleName = "查询是否已经存在常量键", params = {"count","keyword"})
	@RequestMapping(value = "/countByKeyword",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Boolean> countByKeyword(@RequestParam("count") int count, @RequestParam("keyword") String keyword) {
		
		boolean b = baseConstantService.countByKeyword(keyword, count);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", b);
	    return map;
	}
	
	@SysLog(moduleName = "系统常量管理", submoduleName = "保存新增信息", params = {})
	@RequestMapping(value = "/stats",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Long> stats() {
		return baseConstantService.stats();
	}

}