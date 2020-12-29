package com.dzb.console.controller;

import java.util.List;
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

import com.dzb.console.entity.BaseTypeCacheEntity;
import com.dzb.console.entity.BaseTypeEntity;
import com.dzb.console.entity.BaseTypeTreeEntity;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.lang.tree.Tree;

/**
 * @Description t_base_type 表Controller类
 * @author 38840472@qq.com
 * @date 2020-05-12 17:51:09
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("btc")
public class BaseTypeController{

	private static final Logger log = LoggerFactory.getLogger(BaseTypeController.class);

	@Autowired
	private BaseTypeService baseTypeService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
  	@SysLog(moduleName = "数据字典管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "console/t_base_type_load";
	}
  	
  	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
  	@SysLog(moduleName = "数据字典管理", submoduleName = "初始化树形数据字典页面", params = {})
	@RequestMapping(value = "/loadTree",method = RequestMethod.GET)
	public String loadTree() {
		
		log.debug("初始化树形数据字典页面");
		return "console/t_base_type_tree_load";
	}
  	
  	/**
	 * @Description 初始化demo页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
  	@SysLog(moduleName = "数据字典管理", submoduleName = "初始化demo页面", params = {})
	@RequestMapping(value = "/demo",method = RequestMethod.GET)
	public String demo() {
		
		log.debug("初始化demo页面");
		return "demo/t_base_type_list";
	}
  	
	@RequestMapping(value = "/layout",method = RequestMethod.GET)
	public String layout() {
		
		return "demo/layout";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "数据字典管理", submoduleName = "查询列表", params = {"offset","limit","baseTypeEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseTypeEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, @RequestParam(value="type",required=false,defaultValue="0") int type, Model model) {
		
		BaseTypeEntity baseTypeEntity = new BaseTypeEntity();
		baseTypeEntity.setType(type);
		return baseTypeService.getPagination(offset, limit ,baseTypeEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseTypeEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "数据字典管理", submoduleName = "保存新增信息", params = {"baseTypeEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(BaseTypeEntity baseTypeEntity,BindingResult result) {
		
		return baseTypeService.insert(baseTypeEntity);
	}
	
	/**
	 * @Description 初始化编辑信息
	 * @param baseTypeId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "数据字典管理", submoduleName = "初始化编辑信息", params = {"baseTypeId"})
	@RequestMapping(value = "/loadEdit",method = RequestMethod.GET)
	@ResponseBody
	public BaseTypeEntity loadEdit(@RequestParam("baseTypeId") String baseTypeId,Model model) {
		
		return baseTypeService.getByPkey(baseTypeId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param baseTypeEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "数据字典管理", submoduleName = "保存编辑信息", params = {"baseTypeEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(BaseTypeEntity baseTypeEntity,BindingResult result) {
		
		return baseTypeService.update(baseTypeEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "数据字典管理", submoduleName = "删除信息", params = {"baseTypeId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("baseTypeId") String baseTypeId) {
		
		return baseTypeService.delete(baseTypeId);
	}
	
	/**
	 * @Description 通过代码查询列表数据
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 09:26:11
	 */
	@RequestMapping(value = "/getListByCode",method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> getListByCode(@RequestParam("code") String code) {
		
		return baseTypeService.getCacheMapToList(code);
	}
	
	/**
	 * @Description 通过代码和值查询列表数据
	 * @param code
	 * @param value
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:18:51
	 */
	@RequestMapping(value = "/getListNameByCodeAndValue",method = RequestMethod.GET)
	@ResponseBody
	public BaseTypeCacheEntity getListNameByCodeAndValue(@RequestParam("code") String code, @RequestParam("value") String value, Model model) {
		
		return baseTypeService.getCacheItem(code, value);
	}
	
	/**
	 * @Description 通过代码查询树型数据
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	@RequestMapping(value = "/getTreeByCode",method = RequestMethod.GET)
	@ResponseBody
	public List<Tree<String>> getTreeByCode(@RequestParam("code") String code, Model model) {
		
		return baseTypeService.getTreeByCode(code);
	}
	
	/**
	 * @Description 通过代码和值查询树型数据
	 * @param code
	 * @param value
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:19:14
	 */
	@RequestMapping(value = "/getTreeNameByCodeAndValue",method = RequestMethod.GET)
	@ResponseBody
	public BaseTypeTreeEntity getTreeNameByCodeAndValue(@RequestParam("code") String code, @RequestParam("value") String value, Model model) {
		
		return baseTypeService.getBaseTypeTreeByCodeAndValue(code, value);
	}
	
	/**
	 * @Description 查看缓存状态
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 17:44:09
	 */
	@RequestMapping(value = "/stats",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Long> stats() {
		
		return baseTypeService.stats();
	}
	
	/**
	 * @Description 清空缓存状态
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 17:44:09
	 */
	@RequestMapping(value = "/invalidate",method = RequestMethod.GET)
	@ResponseBody
	public boolean invalidate() {
		
		baseTypeService.invalidateAll();
		return true;
	}
	
	/**
	 * @Description 清空缓存状态
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 17:44:09
	 */
	@RequestMapping(value = "/invalidateByCode",method = RequestMethod.GET)
	@ResponseBody
	public boolean invalidateByCode(@RequestParam("code") String code) {
		
		baseTypeService.invalidate(code);
		return true;
	}
	

}