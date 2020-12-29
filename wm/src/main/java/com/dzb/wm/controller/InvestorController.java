package com.dzb.wm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.InvestorEntity;
import com.dzb.wm.service.InvestorService;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_investor 表Controller类
 * @author daizb@hymake.com
 * @date 2020-08-11 19:02:47
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("ic")
public class InvestorController{

	private static final Logger log = LoggerFactory.getLogger(InvestorController.class);

	@Autowired
	private InvestorService investorService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
  	@SysLog(moduleName = "#", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		log.debug("初始化列表页面");
		return "wm/t_investor_load";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	@SysLog(moduleName = "#", submoduleName = "查询列表", params = {"offset","limit","investorEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<InvestorEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,InvestorEntity investorEntity, Model model) {
		
		return investorService.getPagination(offset, limit ,investorEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param investorEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	@SysLog(moduleName = "#", submoduleName = "保存新增信息", params = {"investorEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(InvestorEntity investorEntity,BindingResult result) {
		
		return investorService.insert(investorEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param investorId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	@SysLog(moduleName = "#", submoduleName = "通过主键获取信息", params = {"investorId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public InvestorEntity view(@RequestParam("investorId") long investorId,Model model) {
		
		return investorService.getByPkey(investorId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param investorEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	@SysLog(moduleName = "#", submoduleName = "保存编辑信息", params = {"investorEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(InvestorEntity investorEntity,BindingResult result) {
		
		return investorService.update(investorEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param investorId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	@SysLog(moduleName = "#", submoduleName = "删除信息", params = {"investorId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("investorId") long investorId) {
		
		return investorService.delete(investorId);
	}
	
	/**
	 * @Description 发布新闻资讯
	 * @param newsId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:10:18
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "发布新闻资讯", params = {"investorId", "pushTime"})
	@RequestMapping(value = "/push",method = RequestMethod.POST)
	@ResponseBody
	public int push(@RequestParam("investorId") long investorId, @DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam("pushTime") Date pushTime) {
		
		return investorService.updatePushTimeByInvestorId(investorId, pushTime);
	}
	
	/**
	 * @Description 下架新闻资讯
	 * @param newsId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:10:18
	 */
	@SysLog(moduleName = "新闻资讯管理", submoduleName = "下架新闻资讯", params = {"investorId"})
	@RequestMapping(value = "/pull",method = RequestMethod.POST)
	@ResponseBody
	public int pull(@RequestParam("investorId") long investorId) {
		
		return investorService.updatePushTimeByInvestorId(investorId, null);
	}

}