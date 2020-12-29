package com.hymake.web.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 错误页面统一处理
 */
@Component
public class ErrorConfig implements ErrorPageRegistrar {

    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[]{
                new ErrorPage(HttpStatus.NOT_FOUND, "/common/error-404"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/common/error-500"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/common/error-403")
        };
        registry.addErrorPages(errorPages);
    }
}