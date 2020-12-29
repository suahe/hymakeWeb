package com.dzb.console.security;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


/**
 * @Description: 通过Security框架获取客户端信息
 * @author 38840472@qq.com
 * @date 2017年12月7日 上午10:28:45
 */

public class ClientInfoUtil {
	
	/**
	 * @Description 获取客户端IP地址信息
	 * @param authentication
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 10:49:10
	 */
	public static String getRemoteAddress(Authentication authentication){
		
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails)authentication.getDetails();
		String remoteAddress = webAuthenticationDetails.getRemoteAddress();
		return remoteAddress;
	}
	
	/**
	 * @Description 获取客户端的IP地址
	 * @param request
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-21 21:37:40
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            //X-Real-IP：nginx服务代理
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {

            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {

            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    } 
	
	/**
	 * @Description 获取SessionId
	 * @param authentication
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 10:49:23
	 */
	public static String getSessionId(Authentication authentication){
		
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails)authentication.getDetails();
		String sessionId = webAuthenticationDetails.getSessionId();
		return sessionId;
	}
	
	/**
	 * @Description 获取SessionId
	 * @param request
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-25 20:41:49
	 */
	public static String getSessionId(HttpServletRequest request){
		
		return request.getSession().getId();
	}
	
}
