package com.dzb.console.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseTypeTreeEntity;
import com.dzb.console.service.BaseTypeTreeService;
import com.dzb.console.syslog.SysLog;

import cn.hutool.core.lang.tree.Tree;

/**
 * @Description t_base_type_tree 表Controller类
 * @author 38840472@qq.com
 * @date 2020-05-14 10:41:46
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bttc")
public class BaseTypeTreeController{

	private static final Logger log = LoggerFactory.getLogger(BaseTypeTreeController.class);

	@Autowired
	private BaseTypeTreeService baseTypeTreeService;
	
	/**
	 * @Description 查询树信息
	 * @param baseTypeTreeEntity
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-07 17:01:20
	 */
	@SysLog(moduleName = "树型数据字典管理", submoduleName = "查询列表", params = {"baseTypeTreeEntity"})
	@RequestMapping(value = "/tree",method = RequestMethod.GET)
	@ResponseBody
	public List<Tree<String>> tree(BaseTypeTreeEntity baseTypeTreeEntity, Model model) {
		
		log.debug("初始化列表页面");
		return baseTypeTreeService.getTree(baseTypeTreeEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseTypeTreeEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	@SysLog(moduleName = "树型数据字典管理", submoduleName = "保存新增信息", params = {"baseTypeId", "baseTypeTreePid", "name", "value"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(@RequestParam("baseTypeId") String baseTypeId, @RequestParam("baseTypeTreePid") String baseTypeTreePid, @RequestParam("name") String name, @RequestParam("value") String value) {
		
		return baseTypeTreeService.insert(baseTypeId, baseTypeTreePid, name, value);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param baseTypeTreeId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	@SysLog(moduleName = "树型数据字典管理", submoduleName = "通过主键获取信息", params = {"baseTypeTreeId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public BaseTypeTreeEntity view(@RequestParam("baseTypeTreeId") String baseTypeTreeId,Model model) {
		
		return baseTypeTreeService.getByPkey(baseTypeTreeId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param baseTypeTreeEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	@SysLog(moduleName = "树型数据字典管理", submoduleName = "保存编辑信息", params = {"baseTypeTreeId", "baseTypeTreePid", "name", "value"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(@RequestParam("baseTypeTreeId") String baseTypeTreeId, @RequestParam("baseTypeTreePid") String baseTypeTreePid, @RequestParam("name") String name, @RequestParam("value") String value) {
		
		return baseTypeTreeService.update(baseTypeTreeId, baseTypeTreePid, name, value);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	@SysLog(moduleName = "树型数据字典管理", submoduleName = "删除信息", params = {"baseTypeTreeId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("baseTypeTreeId") String baseTypeTreeId) {
		
		return baseTypeTreeService.delete(baseTypeTreeId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "排序下降", params = {"baseTypeTreeId"})
	@RequestMapping(value = "/down",method = RequestMethod.GET)
	@ResponseBody
	public int down(@RequestParam("baseTypeTreeId") String baseTypeTreeId) {
		
		return baseTypeTreeService.down(baseTypeTreeId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "排序上升", params = {"baseTypeTreeId"})
	@RequestMapping(value = "/up",method = RequestMethod.GET)
	@ResponseBody
	public int up(@RequestParam("baseTypeTreeId") String baseTypeTreeId) {
		
		return baseTypeTreeService.up(baseTypeTreeId);
	}

}