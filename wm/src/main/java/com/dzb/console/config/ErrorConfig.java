package com.dzb.console.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Description 全局公用页面设置
 * @author 38840472@qq.com
 * @date 2019-01-27 17:12:29
 */
@Component
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
    	
        ErrorPage[] errorPages=new ErrorPage[3];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND,"/error-404");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error-500");
        errorPages[2] = new ErrorPage(HttpStatus.FORBIDDEN,"/error-403");

        registry.addErrorPages(errorPages);
    }
}