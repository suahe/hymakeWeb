package com.dzb.console.controller;

import java.util.List;

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

import com.dzb.console.entity.BaseDepartmentEntity;
import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.service.BaseDepartmentService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.lang.tree.Tree;

/**
 * @Description 部门信息管理Controller类
 * @author 38840472@qq.com
 * @date 2020-05-26 23:22:37
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bdc")
public class BaseDepartmentController{

	private static final Logger log = LoggerFactory.getLogger(BaseDepartmentController.class);

	@Autowired
	private BaseDepartmentService baseDepartmentService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
  	@SysLog(moduleName = "部门信息管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "console/t_base_department_load";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseDepartmentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "查询部门信息树", params = {})
	@RequestMapping(value = "/tree",method = RequestMethod.GET)
	@ResponseBody
	public List<Tree<String>> tree() {
		
		return baseDepartmentService.getDepartmentTree();
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseDepartmentEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "保存新增信息", params = {"baseDepartmentEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(BaseDepartmentEntity baseDepartmentEntity,BindingResult result) {
		
		return baseDepartmentService.insert(baseDepartmentEntity);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param departmentId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "通过主键获取信息", params = {"departmentId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public BaseDepartmentEntity view(@RequestParam("departmentId") String departmentId,Model model) {
		
		return baseDepartmentService.getByPkey(departmentId);
	}
	
	/**
	 * @Description 保存编辑信息
	 * @param baseDepartmentEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "保存编辑信息", params = {"baseDepartmentEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(BaseDepartmentEntity baseDepartmentEntity,BindingResult result) {
		
		return baseDepartmentService.update(baseDepartmentEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "删除信息", params = {"departmentId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("departmentId") String departmentId) {
		
		return baseDepartmentService.delete(departmentId);
	}
	
	/**
	 * @Description 通过部门编号查询不属于该部门的用户信息
	 * @param offset  分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param roleId 角色编号
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:32:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "通过部门编号查询不属于该部门的用户信息（Ajax）", params = {"offset","limit","departmentId","searchText"})
	@RequestMapping(value = "/uncheckedList",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> uncheckedList(@RequestParam(value="offset",required=false,defaultValue="1") int offset, @RequestParam(value="limit",required=false,defaultValue="10") int limit, String departmentId, String searchText, Model model) {
		
		return baseDepartmentService.getUnUserByDepartmentIdPagination(offset, limit ,departmentId, searchText);
	}
	
	/**
	 * @Description 通过部门编号查询属于该部门的用户信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param roleId 角色编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:46:22
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "通过部门编号查询属于该部门的用户信息", params = {"offset","limit","departmentId"})
	@RequestMapping(value = "/checkedList",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> checkedList(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, String departmentId, Model model) {
		
		return baseDepartmentService.getUserByDepartmentIdPagination(offset, limit ,departmentId);
	}
	
	/**
	 * @Description 新增部门人员对应信息
	 * @param roleId 角色编号
	 * @param userIds 用户编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:46:59
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "新增部门人员对应信息（Ajax）", params = {"departmentId","userIds"})
	@RequestMapping(value = "/au2r",method = RequestMethod.POST)
	@ResponseBody
	public int addUser2Role(String departmentId,String[] userIds) {
		
		return baseDepartmentService.insertDepartmentIdAndUserIds(departmentId, userIds);
	}
	
	/**
	 * @Description 删除部门人员对应信息
	 * @param roleId 角色编号
	 * @param userIds 用户编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:27:36
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "删除部门人员对应信息", params = {"departmentId","userIds"})
	@RequestMapping(value = "/delByUD",method = RequestMethod.POST)
	@ResponseBody
	public int deleteByUserIdAndDepartmentId(String departmentId,String[] userIds) {
		
		return baseDepartmentService.deleteByUserIdAndDepartmentId(departmentId, userIds);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param departmentId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@SysLog(moduleName = "部门信息管理", submoduleName = "通过用户编号获取部门信息", params = {"userId"})
	@RequestMapping(value = "/viewByUserId",method = RequestMethod.GET)
	@ResponseBody
	public BaseDepartmentEntity viewByUserId(@RequestParam("userId") String userId,Model model) {
		
		return baseDepartmentService.getDepartmentByUserId(userId);
	}

}