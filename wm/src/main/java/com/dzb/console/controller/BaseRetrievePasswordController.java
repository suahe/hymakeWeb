package com.dzb.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.service.BaseRetrievePasswordService;
import com.dzb.console.syslog.SysLog;

/**
 * @Description 找回密码控制器类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:33:32
 */
@Controller
@RequestMapping("brpc")
public class BaseRetrievePasswordController{
	
	@Autowired
	private BaseRetrievePasswordService baseRetrievePasswordService;

	@SysLog(moduleName = "找回密码管理", submoduleName = "发送验证邮件", params = {})
	@RequestMapping(value = "/sendMail",method = RequestMethod.GET)
	@ResponseBody
	public int sendMail(String email,String remoteAddress,String area,Model model) {
		
		return baseRetrievePasswordService.sendMail(email,remoteAddress,area);
	}
	
	@SysLog(moduleName = "找回密码管理", submoduleName = "验证验证码信息", params = {"mail","securityCode"})
	@RequestMapping(value = "/vsc",method = RequestMethod.POST)
	public String verifySecurityCode(String email,String securityCode,Model model) {
		
		boolean b = baseRetrievePasswordService.verifySecurityCode(email, securityCode);
		model.addAttribute("email", email);
		model.addAttribute("securityCode",securityCode);
		if(b) {
			return "console/retrieve_password_resetpassword";
		}else {
			model.addAttribute("securityCodeState", -1);
			return "console/retrieve_password";
		}
	}
	
	/**
	 * @Description 初始化页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	@SysLog(moduleName = "找回密码管理", submoduleName = "重置密码", params = {})
	@RequestMapping(value = "/rp",method = RequestMethod.POST)
	public String resetPassword(String email,String securityCode,String lp,Model model) {
		
		boolean b = baseRetrievePasswordService.resetPassword(email, securityCode, lp);
		if(b) {
			return "console/retrieve_password_success";
		}else {
			return "console/retrieve_password";
		}
		
	}
}
