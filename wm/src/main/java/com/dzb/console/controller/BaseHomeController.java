package com.dzb.console.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dzb.console.security.ClientInfoUtil;
import com.dzb.console.security.IpAttemptService;
import com.dzb.console.syslog.SysLog;



/**
 * @Description 框架基础控制器
 * @author 38840472@qq.com
 * @date 2019-01-27 16:43:31
 */
@Controller
public class BaseHomeController {
	
	@Autowired
	private IpAttemptService ipAttemptService;
	
	/**
	 * @Description 登录页面
	 * @return
	 * @author 38840472@qq.com
	 * @throws IOException 
	 * @date 2019-01-27 16:44:49
	 */
	@SysLog(moduleName = "框架管理", submoduleName = "加载登录页面", params = {})
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
    	
    	String cip =  ClientInfoUtil.getRemoteAddress(request);
    	boolean b = ipAttemptService.isBlocked(cip);
    	model.addAttribute("showCodeAuthenctiation", b);
        return "login";
    }
    
    /**
     * @Description 默认访问页面
     * @return
     * @author 38840472@qq.com
     * @date 2019-01-27 16:45:25
     */
	@SysLog(moduleName = "框架管理", submoduleName = "加载主页", params = {})
    @RequestMapping("/")
    public String main() {
    	return "redirect:main/load";
    }
    
    /**
     * @Description 404页面
     * @return
     * @author 38840472@qq.com
     * @date 2019-01-27 16:46:15
     */
	@SysLog(moduleName = "框架管理", submoduleName = "加载404页面", params = {})
    @RequestMapping("error-404")
	public String error404(){
	    return "error/404";
	}
	
    /**
     * @Description 403页面
     * @return
     * @author 38840472@qq.com
     * @date 2019-01-27 16:46:15
     */
	@SysLog(moduleName = "框架管理", submoduleName = "加载403页面", params = {})
	@RequestMapping("error-403")
	public String error403(){
	    return "error/403";
	}
	
	 /**
     * @Description 500页面
     * @return
     * @author 38840472@qq.com
     * @date 2019-01-27 16:46:15
     */
	@SysLog(moduleName = "框架管理", submoduleName = "加载500页面", params = {})
	@RequestMapping("error-500")
    public String error500(){
        return "error/500";
    }
    
    
}
