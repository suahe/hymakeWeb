package com.dzb.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.entity.BaseUserExtEntity;
import com.dzb.console.entity.MediaFileEntity;
import com.dzb.console.service.BaseUserExtService;
import com.dzb.console.service.BaseUserPartraitService;
import com.dzb.console.service.BaseUserService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Description t_base_user 表Controller类
 * @author 38840472@qq.com
 * @date 2018-12-18 16:24:42
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("buc")
public class BaseUserController {

	@Autowired
	private BaseUserService baseUserService;
	@Autowired
	private BaseUserExtService baseUserExtService;
	@Autowired
	private BaseUserPartraitService baseUserPartraitService;
	
	/**
	 * @Description 初始化页面
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-11 08:52:13
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "初始化页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		return "console/t_base_user_load";
	}
	
	/**
	 * @Description 初始化用户信息
	 * @param offset 分页起始位置
	 * @param limit 分页度量信息
	 * @param model
	 * @param searchText 查询条件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "初始化用户信息（Ajax）", params = {"offset","limit","searchText"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseUserEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, String searchText) {
		
		return baseUserService.selectPagination(offset, limit ,searchText);
	}
	
	/**
	 * @Description 新增用户信息
	 * @param baseUserEntity 用户信息实体
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "新增用户信息", params = {"baseUserEntity"})
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public int add(BaseUserEntity baseUserEntity,BindingResult result) {
		
		return baseUserService.insert(baseUserEntity);
	}
	
	/**
	 * @Description 初始化编辑页面
	 * @param userId 用户编号
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "初始化编辑页面", params = {"userId"})
	@RequestMapping(value = "/loadEdit",method = RequestMethod.GET)
	@ResponseBody
	public BaseUserEntity loadEdit(@RequestParam("userId") String userId) {
		
		return baseUserService.getByPkey(userId);
	}
	
	/**
	 * @Description "编辑用户信息
	 * @param baseUserEntity 用户信息实体
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "编辑用户信息", params = {"baseUserEntity"})
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public int edit(BaseUserEntity baseUserEntity,BindingResult result) {
		
		return baseUserService.update(baseUserEntity);
	}
	
	/**
	 * @Description 删除用户信息
	 * @param userId 用户编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "锁定/解锁用户信息", params = {"userId"})
	@RequestMapping(value = "/updateIsDel",method = RequestMethod.GET)
	@ResponseBody
	public int updateIsDel(@RequestParam("userId") String userId) {
		
		return baseUserService.updateIsDel(userId);
	}
	
	/**
	 * @Description 初始化修改密码页面
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-11 08:52:13
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "初始化修改密码页面", params = {})
	@RequestMapping(value = "/loadCp",method = RequestMethod.GET)
	public String loadCp(Model model) {
		
		return "console/change_password";
	}
	
	/**
	 * @Description 修改密码
	 * @param originalPassword
	 * @param latestPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-02-14 09:22:39
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "修改密码", params = {})
	@RequestMapping(value = "/cp",method = RequestMethod.POST)
	@ResponseBody
	public boolean changePassword(@RequestParam(value="op",required=true) String originalPassword, @RequestParam(value="lp",required=true) String latestPassword) {
		
		return baseUserService.changePassword(originalPassword, latestPassword);
	}
	
	/**
	 * @Description 初始化视图信息
	 * @param userExtId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	@RequestMapping(value = "/loadViewExt",method = RequestMethod.GET)
	@ResponseBody
	public BaseUserExtEntity loadViewExt(@RequestParam("userId") String userId,Model model) {
		
		return baseUserExtService.getByUserId(userId);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param baseUserExtEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	@SysLog(moduleName = "用户管理", submoduleName = "保存用户扩展信息", params = {"baseUserExtEntity"})
	@RequestMapping(value = "/saveExt",method = RequestMethod.POST)
	@ResponseBody
	public String saveExt(BaseUserExtEntity baseUserExtEntity,BindingResult result) {
		
		return baseUserExtService.save(baseUserExtEntity);
	}
	
	@RequestMapping(value = "/upi" ,method = RequestMethod.POST)
	@ResponseBody
	public int updatePatraitImage(@RequestParam("userId") String userId, @RequestParam("images") MultipartFile images) {
		
		return baseUserPartraitService.save(userId, images);
	}
	
	@GetMapping(value= "/loadPartrait")
    @ResponseBody
    public ResponseEntity<byte[]> loadPartrait(@RequestParam("userId") String userId) {
        
    	MediaFileEntity mediaFileEntity = baseUserPartraitService.load(userId);
    	if(ObjectUtil.isNotNull(mediaFileEntity)) {
    		final HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(mediaFileEntity.getMediaType());
    		return new ResponseEntity<>(mediaFileEntity.getMedia(), headers, HttpStatus.OK);
    	} else {
    		return null;
    	}
    }
    

}