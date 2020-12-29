package com.dzb.wm.controller;

import java.util.Date;

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

import com.dzb.wm.entity.JoinEntity;
import com.dzb.wm.service.JoinService;

import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_join 表Controller类
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("jc")
public class JoinController{

	private static final Logger log = LoggerFactory.getLogger(JoinController.class);

	@Autowired
	private JoinService joinService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
  	@SysLog(moduleName = "招聘信息管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "wm/t_join_load";
	}
  	
  	@SysLog(moduleName = "招聘信息管理", submoduleName = "初始化新增页面", params = {})
	@RequestMapping(value = "/loadAdd",method = RequestMethod.GET)
	public String loadAdd() {
		
		log.debug("初始化列表页面");
		return "wm/t_join_add";
	}
  	
  	@SysLog(moduleName = "招聘信息管理", submoduleName = "初始化编辑页面", params = {})
	@RequestMapping(value = "/loadEdit",method = RequestMethod.GET)
	public String loadEdit(@RequestParam("joinId") long joinId,Model model) {
		
		log.debug("初始化列表页面");
		JoinEntity joinEntity = joinService.getByPkey(joinId);
		model.addAttribute("joinEntity", joinEntity);
		return "wm/t_join_edit";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@SysLog(moduleName = "招聘信息管理", submoduleName = "查询列表", params = {"offset","limit","joinEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<JoinEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,JoinEntity joinEntity, Model model) {
		
		return joinService.getPagination(offset, limit ,joinEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param joinEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@SysLog(moduleName = "招聘信息管理", submoduleName = "保存新增信息", params = {"joinEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(JoinEntity joinEntity,BindingResult result) {
		
		return joinService.insert(joinEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param joinId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@SysLog(moduleName = "招聘信息管理", submoduleName = "通过主键获取信息", params = {"joinId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public JoinEntity view(@RequestParam("joinId") long joinId,Model model) {
		
		return joinService.getByPkey(joinId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param joinEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@SysLog(moduleName = "招聘信息管理", submoduleName = "保存编辑信息", params = {"joinEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(JoinEntity joinEntity,BindingResult result) {
		
		return joinService.update(joinEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param joinId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@SysLog(moduleName = "招聘信息管理", submoduleName = "删除信息", params = {"joinId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("joinId") long joinId) {
		
		return joinService.delete(joinId);
	}
	
	@SysLog(moduleName = "招聘信息管理", submoduleName = "发布招聘信息", params = {"joinId"})
	@RequestMapping(value = "/push",method = RequestMethod.POST)
	@ResponseBody
	public int push(@RequestParam("joinId") long joinId) {
		
		return joinService.updatePushTimeByJoinId(joinId, new Date());
	}
	
	@SysLog(moduleName = "招聘信息管理", submoduleName = "下架招聘信息", params = {"joinId"})
	@RequestMapping(value = "/pull",method = RequestMethod.POST)
	@ResponseBody
	public int pull(@RequestParam("joinId") long joinId) {
		
		return joinService.updatePushTimeByJoinId(joinId, null);
	}

}