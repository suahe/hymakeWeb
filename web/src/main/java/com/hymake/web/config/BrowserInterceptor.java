package com.hymake.web.config;

import com.hymake.web.util.CompareUtil;
import com.hymake.web.util.MobileUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 浏览器低版本拦截器
 */
public class BrowserInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isMoblie = MobileUtil.JudgeIsMoblie(request);
        //如果是手机端直接跳转
        if(isMoblie==true){
            response.sendRedirect("http://m.hymake.com");
            return false;
        }
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        if (browser.getName().contains("Internet Explorer") && CompareUtil.compareVersion(version.getVersion(), "10.0") == -1) {
            response.sendRedirect("/common/browserTips");
            return false;
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
