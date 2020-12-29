package com.dzb.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.BaseMenuEntity;
import com.dzb.console.entity.BaseRoleTreeEntity;
import com.dzb.console.security.RefreshResourceService;
import com.dzb.console.service.BaseMenuRoleMappingService;
import com.dzb.console.service.BaseMenuService;
import com.dzb.console.syslog.SysLog;

import cn.hutool.core.lang.tree.Tree;

/**
 * @Description t_base_menu 表Controller类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:00:36
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("bmc")
public class BaseMenuController{

	@Autowired
	private BaseMenuService baseMenuService;
	@Autowired
	private BaseMenuRoleMappingService baseMenuRoleMappingService;
	@Autowired
	private RefreshResourceService refreshResourceService;
	
	/**
	 * @Description 刷新菜单数据缓存
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:12:34
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "刷新菜单数据缓存（Ajax）", params = {})
	@RequestMapping(value = "/refresh",method = RequestMethod.GET)
	@ResponseBody
	public boolean refresh() {
		
		return refreshResourceService.refreshCache();
	}
	
	/**
	 * @Description 初始化菜单管理页面
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:12:21
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "初始化菜单管理页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		return "console/t_base_menu_load";
	}
	
	/**
	 * @Description 初始化菜单信息树
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-24 14:01:58
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "初始化菜单信息树（Ajax）", params = {})
	@RequestMapping(value = "/tree",method = RequestMethod.GET)
	@ResponseBody
	public List<Tree<String>> tree(){
		
		return baseMenuService.getMenuTree();
	}
	
	/**
	 * @Description 新增菜单信息
	 * @param menuPid 菜单父编号
	 * @param menuName 菜单名称
	 * @param url 链接地址
	 * @param isLeaf 叶子节点标识
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-24 14:01:50
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "新增菜单信息（Ajax）", params = {"menuPid","menuName","url","isLeaf"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(@RequestParam(value="menuPid",required=true) String menuPid,@RequestParam(value="menuName",required=true) String menuName,@RequestParam(value="url",required=true) String url,@RequestParam(value="iconCss",required=true) String iconCss) {
		
		return baseMenuService.insert(menuPid,menuName,url,iconCss);
	}
	
	/**
	 * @Description 查看菜单信息
	 * @param menuId 菜单编号
	 * @param menuName 菜单名称
	 * @param url 链接地址
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-24 14:01:27
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "查看菜单信息（Ajax）", params = {"menuId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public BaseMenuEntity view(@RequestParam(value="menuId",required=true) String menuId) {
		
		return baseMenuService.getByPkey(menuId);
	}
	

	/**
	 * @Description 编辑菜单信息
	 * @param menuId 菜单编号
	 * @param menuName 菜单名称
	 * @param url 链接地址
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-24 14:01:27
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "编辑菜单信息（Ajax）", params = {"menuId","menuName","url"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(@RequestParam(value="menuId",required=true) String menuId,@RequestParam(value="menuName",required=true) String menuName,@RequestParam(value="url",required=true) String url, @RequestParam(value="iconCss",required=true) String iconCss) {
		
		return baseMenuService.update(menuId,menuName,url,iconCss);
	}
	
	/**
	 * @Description 删除菜单信息
	 * @param menuId 菜单编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:10:34
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "删除菜单信息（Ajax）", params = {"menuId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public boolean del(@RequestParam("menuId") String menuId) {
		
		return baseMenuService.delete(menuId);
	}
	
	/**
	 * @Description 初始化角色信息树
	 * @param menuId 菜单编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:10:16
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "初始化角色信息树（Ajax）", params = {"menuId"})
	@RequestMapping(value = "/roleTree",method = RequestMethod.GET)
	@ResponseBody
	public List<BaseRoleTreeEntity> roleTree(String menuId){
		
		List<BaseRoleTreeEntity> list = baseMenuRoleMappingService.selectTree(menuId);
		return list;
	}
	
	/**
	 * @Description 菜单权限设置
	 * @param menuId 菜单编号
	 * @param roleId 角色编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:09:38
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "菜单权限设置（Ajax）", params = {"menuId","roleId"})
	@RequestMapping(value = "/author",method = RequestMethod.GET)
	@ResponseBody
	public boolean author(String menuId,String roleId) {
		
		return baseMenuRoleMappingService.insert(menuId, roleId);
	}
	
	/**
	 * @Description 清除菜单权限设置
	 * @param menuId 菜单编号
	 * @param roleId 角色编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:09:09
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "清除菜单权限设置（Ajax）", params = {"menuId","roleId"})
	@RequestMapping(value = "/unAuthor",method = RequestMethod.GET)
	@ResponseBody
	public boolean unAuthor(String menuId,String roleId) {
		
		return baseMenuRoleMappingService.delete(menuId, roleId);
	}
	
	/**
	 * @Description 初始化菜单角色信息树
	 * @param menuId 菜单编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 20:08:44
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "初始化菜单角色信息树（Ajax）", params = {"menuId"})
	@RequestMapping(value = "/roleTreeByMenuId",method = RequestMethod.GET)
	@ResponseBody
	public List<BaseRoleTreeEntity> roleTreeByMenuId(String menuId){
		
		List<BaseRoleTreeEntity> list = baseMenuRoleMappingService.selectTree(menuId);
		return list;
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "排序下降", params = {"menuId"})
	@RequestMapping(value = "/down",method = RequestMethod.GET)
	@ResponseBody
	public int down(@RequestParam("menuId") String menuId) {
		
		return baseMenuService.down(menuId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "菜单权限管理", submoduleName = "排序上升", params = {"menuId"})
	@RequestMapping(value = "/up",method = RequestMethod.GET)
	@ResponseBody
	public int up(@RequestParam("menuId") String menuId) {
		
		return baseMenuService.up(menuId);
	}
	

}