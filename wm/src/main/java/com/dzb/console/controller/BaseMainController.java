package com.dzb.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dzb.console.service.BaseMenuService;
import com.dzb.console.service.BaseUserExtService;
import com.dzb.console.service.BaseUserPartraitService;
import com.dzb.console.service.BaseUserService;
import com.dzb.console.syslog.SysLog;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;

/**
 * @Description 系统主页框架控制器
 * @author 38840472@qq.com
 * @date 2019-01-27 16:37:43
 */
@Controller
@RequestMapping("main")
public class BaseMainController{
	
	@Autowired
	private BaseUserService baseUserService;
	@Autowired
	private BaseMenuService baseMenuService;
	@Autowired
	private BaseUserExtService baseUserExtService;
	@Autowired
	private BaseUserPartraitService baseUserPartraitService;

	@RequestMapping("/load")
	public String load(Model model) {
		
		List<Tree<String>> tree = baseMenuService.selectMenuTreeByActiveUser();
		
		model.addAttribute("menuTree", tree);
		return "console/main";
	}
	
	/**
	 * @Description 初始化设置管理页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	@SysLog(moduleName = "系统管理", submoduleName = "初始化设置管理页面", params = {})
	@RequestMapping(value = "/setting",method = RequestMethod.GET)
	public String setting(Model model) {
		
		BaseUserEntity baseUserEntity = baseUserService.getByActiveUser();
		model.addAttribute("baseUser", baseUserEntity);
		BaseUserExtEntity baseUserExtEntity = baseUserExtService.getByByActiveUser();
		model.addAttribute("baseUserExt", baseUserExtEntity);
		return "console/setting";
	}
	
	/**
	 * @Description 保存用户扩展信息
	 * @param baseUserExtEntity
	 * @param result
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	@SysLog(moduleName = "系统管理", submoduleName = "保存用户扩展信息", params = {"baseUserExtEntity"})
	@RequestMapping(value = "/saveExt",method = RequestMethod.POST)
	@ResponseBody
	public String saveExt(BaseUserExtEntity baseUserExtEntity,BindingResult result) {
		
		return baseUserExtService.save(baseUserExtEntity);
	}
	
	/**
	 * @Description 修改当前用户密码
	 * @param originalPassword
	 * @param latestPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 11:21:24
	 */
	@SysLog(moduleName = "系统管理", submoduleName = "修改当前用户密码", params = {})
	@RequestMapping(value = "/cp",method = RequestMethod.POST)
	@ResponseBody
	public boolean changePassword(@RequestParam(value="op",required=true) String originalPassword, @RequestParam(value="lp",required=true) String latestPassword) {
		
		return baseUserService.changePassword(originalPassword, latestPassword);
	}
	
	/**
	 * @Description 验证当前用户密码是否正确
	 * @param originalPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 11:21:43
	 */
	@SysLog(moduleName = "系统管理", submoduleName = "验证当前用户密码是否正确", params = {})
	@RequestMapping(value = "/validatePasswordByActiveUser",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> validatePasswordByActiveUser(@RequestParam(value="op",required=true) String originalPassword) {
		
		boolean b = baseUserService.validatePasswordByActiveUser(originalPassword);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", b);
	    return map;
	}
	
	/**
	 * @Description 获取当前用户头像信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 11:21:53
	 */
	@SysLog(moduleName = "系统管理", submoduleName = "获取当前用户头像信息", params = {})
	@GetMapping(value= "/loadPartraitByActiveUser")
    @ResponseBody
    public ResponseEntity<byte[]> loadPartraitByActiveUser() {
        
    	MediaFileEntity mediaFileEntity = baseUserPartraitService.loadByActiveUser();
    	if(ObjectUtil.isNotNull(mediaFileEntity)) {
    		final HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(mediaFileEntity.getMediaType());
    		return new ResponseEntity<>(mediaFileEntity.getMedia(), headers, HttpStatus.OK);
    	} else {
    		return null;
    	}
    }
    
    /**
     * @Description 更新当前用户头像信息
     * @param userId
     * @param images
     * @return
     * @author 38840472@qq.com
     * @date 2020-07-06 11:22:00
     */
    @SysLog(moduleName = "系统管理", submoduleName = "更新当前用户头像信息", params = {"baseUserExtEntity"})
    @RequestMapping(value = "/upi" ,method = RequestMethod.POST)
	@ResponseBody
	public int updatePatraitImage(@RequestParam("userId") String userId, @RequestParam("images") MultipartFile images) {
		
		return baseUserPartraitService.save(userId, images);
	}
}
