package com.dzb.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseUserlogEntity;
import com.dzb.console.service.BaseUserlogService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_userlog 表Controller类
 * @author 38840472@qq.com
 * @date 2019-01-03 10:48:20
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bulc")
public class BaseUserlogController {

	@Autowired
	private BaseUserlogService baseUserlogService;
	
	/**
	 * @Description 初始化页面
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-11 08:57:02
	 */
	@SysLog(moduleName = "用户日志管理", submoduleName = "初始化页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		return "console/t_base_userlog_load";
	}
	
	/**
	 * @Description 初始化用户登录日志信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-11 08:55:56
	 */
	@SysLog(moduleName = "用户日志管理", submoduleName = "初始化用户登录日志信息（Ajax）", params = {"offset","limit","searchText"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserlogEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String searchText) {
		
		return baseUserlogService.selectPagination(offset, limit ,searchText);
	}

}