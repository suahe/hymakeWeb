package com.dzb.console.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @Description 认证表单附加属性处理类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:50:34
 */

public class WebWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = -4314188969172981915L;
	
	private final String cip;

    public WebWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        cip = request.getParameter("cip");
    }

    public String getCip() {
        return cip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Cip: ").append(this.getCip());
        return sb.toString();
    }
}
