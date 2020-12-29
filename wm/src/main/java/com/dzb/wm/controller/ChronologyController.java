package com.dzb.wm.controller;

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

import com.dzb.wm.entity.ChronologyEntity;
import com.dzb.wm.service.ChronologyService;

import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_chronology 表Controller类
 * @author daizb@hymake.com
 * @date 2020-07-03 13:48:53
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("cc")
public class ChronologyController{

	private static final Logger log = LoggerFactory.getLogger(ChronologyController.class);

	@Autowired
	private ChronologyService chronologyService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
  	@SysLog(moduleName = "年表信息管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "wm/t_chronology_load";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	@SysLog(moduleName = "年表信息管理", submoduleName = "查询列表", params = {"offset","limit","chronologyEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<ChronologyEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,ChronologyEntity chronologyEntity, Model model) {
		
		return chronologyService.getPagination(offset, limit ,chronologyEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param chronologyEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	@SysLog(moduleName = "年表信息管理", submoduleName = "保存新增信息", params = {"chronologyEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(ChronologyEntity chronologyEntity,BindingResult result) {
		
		return chronologyService.insert(chronologyEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param chronologyId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	@SysLog(moduleName = "年表信息管理", submoduleName = "通过主键获取信息", params = {"chronologyId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public ChronologyEntity view(@RequestParam("chronologyId") long chronologyId,Model model) {
		
		return chronologyService.getByPkey(chronologyId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param chronologyEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	@SysLog(moduleName = "年表信息管理", submoduleName = "保存编辑信息", params = {"chronologyEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(ChronologyEntity chronologyEntity,BindingResult result) {
		
		return chronologyService.update(chronologyEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param chronologyId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	@SysLog(moduleName = "年表信息管理", submoduleName = "删除信息", params = {"chronologyId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("chronologyId") long chronologyId) {
		
		return chronologyService.delete(chronologyId);
	}

}