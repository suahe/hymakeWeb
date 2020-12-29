package com.hymake.mobileWeb.config;

import com.hymake.mobileWeb.config.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new RequestInterceptor());
        interceptorRegistration.addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif", "/**/*.ico", "/**/fonts/*", "/tipsPage/*", "/error/*"});
    }
}
