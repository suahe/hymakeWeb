package com.hymake.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.hymake.web.service.BaseConstantService;

/**
 * @Description 设置全局变量
 * @author 38840472@qq.com
 * @date 2020-07-02 11:25:01
 */
@Component
public class ViewResolverConfig {
	
	@Autowired
	private BaseConstantService baseConstantService;

    @Autowired
    @Qualifier("thymeleafViewResolver")
    private void myViewConfig(ThymeleafViewResolver thymeleafViewResolver){
        if(thymeleafViewResolver != null){
            thymeleafViewResolver.setStaticVariables(baseConstantService.getWebSite());
        }
    }
}