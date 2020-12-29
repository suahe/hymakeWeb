package com.dzb.console.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @Description csrf例外配置
 * @author 38840472@qq.com
 * @date 2020-04-23 22:33:20
 */
@Component("CsrfSecurityRequestMatcher")
public class CsrfSecurityRequestMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    @Override
    public boolean matches(HttpServletRequest request) {
        if (execludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : execludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    /**
     * 需要排除的url列表
     */
    private final List<String> execludeUrls = new ArrayList<String>() {
		private static final long serialVersionUID = -1221971635468829937L;
	{
        add("/upload");
        add("/bdmc/upload");
    }};
}