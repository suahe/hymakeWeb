package com.dzb.console.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseTypeListEntity;
import com.dzb.console.service.BaseTypeListService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_type_list 表Controller类
 * @author 38840472@qq.com
 * @date 2020-05-13 14:59:08
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("btlc")
public class BaseTypeListController{

	private static final Logger log = LoggerFactory.getLogger(BaseTypeListController.class);

	@Autowired
	private BaseTypeListService baseTypeListService;
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "查询列表", params = {"offset","limit","baseTypeId"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseTypeListEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, String baseTypeId, Model model) {
		
		log.debug("初始化列表页面");
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		baseTypeListEntity.setBaseTypeId(baseTypeId);
		return baseTypeListService.getPagination(offset, limit ,baseTypeListEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseTypeListEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "保存新增信息", params = {"baseTypeId", "name", "value"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(@RequestParam("baseTypeId") String baseTypeId, @RequestParam("name") String name, @RequestParam("value") String value) {
		
		return baseTypeListService.insert(baseTypeId, name, value);
	}
	
	/**
	 * @Description 初始化编辑信息
	 * @param baseTypeListId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "初始化编辑信息", params = {"baseTypeListId"})
	@RequestMapping(value = "/loadEdit",method = RequestMethod.GET)
	@ResponseBody
	public BaseTypeListEntity loadEdit(@RequestParam("baseTypeListId") String baseTypeListId,Model model) {
		
		return baseTypeListService.getByPkey(baseTypeListId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param baseTypeListEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "保存编辑信息", params = {"baseTypeListId", "name", "value"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(@RequestParam("baseTypeListId") String baseTypeListId, @RequestParam("name") String name, @RequestParam("value") String value) {
		
		return baseTypeListService.update(baseTypeListId, name, value);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "删除信息", params = {"baseTypeListId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("baseTypeListId") String baseTypeListId) {
		
		return baseTypeListService.delete(baseTypeListId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "排序下降", params = {"baseTypeListId"})
	@RequestMapping(value = "/down",method = RequestMethod.GET)
	@ResponseBody
	public int down(@RequestParam("baseTypeListId") String baseTypeListId) {
		
		return baseTypeListService.down(baseTypeListId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "数据字典列表项管理", submoduleName = "排序上升", params = {"baseTypeListId"})
	@RequestMapping(value = "/up",method = RequestMethod.GET)
	@ResponseBody
	public int up(@RequestParam("baseTypeListId") String baseTypeListId) {
		
		return baseTypeListService.up(baseTypeListId);
	}

}