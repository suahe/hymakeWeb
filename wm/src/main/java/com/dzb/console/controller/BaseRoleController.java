package com.dzb.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseRoleTreeEntity;
import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.service.BaseRoleService;
import com.dzb.console.service.BaseUserRoleMappingService;
import com.dzb.console.service.BaseUserService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_role 表Controller类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:15
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("brc")
public class BaseRoleController{

	@Autowired
	private BaseRoleService baseRoleService;
	@Autowired
	private BaseUserService baseUserService;
	@Autowired
	private BaseUserRoleMappingService baseUserRoleMappingService;
	
	/**
	 * @Description 初始化角色信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:47:29
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "初始化角色信息（Ajax）", params = {"offset","limit","searchText"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String searchText) {
		
		return baseUserService.selectPagination(offset, limit ,searchText);
	}
	
	/**
	 * @Description 新增角色人员对应信息
	 * @param roleId 角色编号
	 * @param userIds 用户编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:46:59
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "新增角色人员对应信息（Ajax）", params = {"roleId","userIds"})
	@RequestMapping(value = "/au2r",method = RequestMethod.POST)
	@ResponseBody
	public boolean addUser2Role(String roleId,String[] userIds) {
		
		return baseUserRoleMappingService.insertRoleIdAndUserIds(roleId, userIds);
	}
	
	/**
	 * @Description 通过角色编号查询用户信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param roleId 角色编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:46:22
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "通过角色编号查询用户信息（Ajax）", params = {"offset","limit","roleId"})
	@RequestMapping(value = "/checkedList",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> checkedList(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String roleId) {
		
		return baseUserRoleMappingService.getUserByRoleIdPagination(offset, limit ,roleId);
	}
	
	/**
	 * @Description 通过角色编号查询不属于的用户信息
	 * @param offset  分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param roleId 角色编号
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:32:37
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "通过角色编号查询不属于的用户信息（Ajax）", params = {"offset","limit","roleId","searchText"})
	@RequestMapping(value = "/uncheckedList",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> uncheckedList(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String roleId,String searchText) {
		
		return baseUserRoleMappingService.getUnUserByRoleIdPagination(offset, limit ,roleId,searchText);
	}
	
	/**
	 * @Description 初始化页面
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:32:20
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "初始化页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		return "console/t_base_role_load";
	}
	
	/**
	 * @Description 初始化角色树信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:31:57
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "初始化角色树信息（Ajax）", params = {})
	@RequestMapping(value = "/tree",method = RequestMethod.GET)
	@ResponseBody
	public List<BaseRoleTreeEntity> tree(){
		
		List<BaseRoleTreeEntity> list = baseRoleService.selectTree();
		return list;
	}
	
	/**
	 * @Description 新增角色信息
	 * @param rolePid 角色父编号
	 * @param roleName 角色名称
	 * @param isLeaf 叶子节点标识
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:28:29
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "新增角色信息（Ajax）", params = {"rolePid","roleName","isLeaf"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public boolean add(@RequestParam(value="rolePid",required=true) String rolePid,@RequestParam(value="roleName",required=true) String roleName,@RequestParam(value="isLeaf",required=true) int isLeaf) {
		
		return baseRoleService.insert(rolePid,roleName,isLeaf);
	}
	
	/**
	 * @Description 编辑角色信息
	 * @param roleId 角色编号
	 * @param roleName 角色名称
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:28:07
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "编辑角色信息（Ajax）", params = {"roleId","roleName"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public boolean edit(@RequestParam(value="roleId",required=true) String roleId,@RequestParam(value="roleName",required=true) String roleName) {
		
		return baseRoleService.update(roleId,roleName);
	}
	
	/**
	 * @Description 删除角色用户映射
	 * @param roleId 角色编号
	 * @param userIds 用户编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:27:36
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "删除角色用户映射（Ajax）", params = {"roleId","userIds"})
	@RequestMapping(value = "/delByUR",method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteByUserIdAndRoleId(String roleId,String[] userIds) {
		
		return baseUserRoleMappingService.deleteByUserIdAndRoleId(roleId, userIds);
	}
	
	/**
	 * @Description 删除角色信息
	 * @param roleId 角色编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:27:23
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "删除角色信息（Ajax）", params = {"roleId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public boolean del(@RequestParam("roleId") String roleId) {
		return baseRoleService.delete(roleId);
	}
	
	/**
	 * @Description 通过角色编号查询用户信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量长度
	 * @param model
	 * @param roleId 角色编号
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:46:22
	 */
	@SysLog(moduleName = "角色管理", submoduleName = "通过角色编号查询用户信息（Ajax）", params = {"offset","limit","roleId","searchText"})
	@RequestMapping(value = "/searchList",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> searchList(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, Model model,String roleId,String searchText) {
		
		return baseUserRoleMappingService.searchUserByRoleIdPagination(offset, limit ,roleId,searchText);
	}
}