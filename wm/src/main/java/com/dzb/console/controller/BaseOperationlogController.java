package com.dzb.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseOperationlogEntity;
import com.dzb.console.service.BaseOperationlogService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_operationlog 表Controller类
 * @author 38840472@qq.com
 * @date 2019-01-10 14:47:58
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bolc")
public class BaseOperationlogController{

	@Autowired
	private BaseOperationlogService baseOperationlogService;
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseOperationlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	@SysLog(moduleName = "操作日志管理", submoduleName = "初始化日志信息", params = {"offset","limit","searchText"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseOperationlogEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String searchText) {
		
		return  baseOperationlogService.selectPagination(offset, limit ,searchText);
	}
	
	/**
	 * @Description 初始化编辑信息页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	@SysLog(moduleName = "操作日志管理", submoduleName = "初始化页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		return "console/t_base_operationlog_load";
	}
	

}