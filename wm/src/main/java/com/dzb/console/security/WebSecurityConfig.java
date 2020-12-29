package com.dzb.console.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.dzb.console.config.MarvelConfig;

/**
 * @Description 权限认证配置类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:50:02
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebAuthenctiationSuccessHandler webAuthenctiationSuccessHandler;
    @Autowired
    private WebAuthenctiationFailureHandler webAuthenctiationFailureHandler;
    @Autowired
    private WebAuthenctiationLogoutSuccessHandler webAuthenctiationLogoutSuccessHandler;
    @Autowired
    private RememberAuthenctiationSuccessHandler rememberAuthenctiationSuccessHandler;
    @Autowired
    private WebFilterAbstractSecurityInterceptor webFilterAbstractSecurityInterceptor;
    @Autowired
    private WebAuthenticationDetailsSource webAuthenticationDetailsSource;
    @Autowired
    private WebAuthenticationProvider webAuthenticationProvider;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher;
    @Autowired
    private RememberUserDetailsService rememberUserDetailsService;
    @Resource
    private DataSource dataSource;
    @Autowired
    private MarvelConfig marvelConfig;
    
    /** 记住登录密钥 */
    private final static String INTERNAL_SECRET_KEY = "29003@dzb";

    /**
      * 配置Security资源信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	http.csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher)
    	.and()
	    	.authorizeRequests()
	    	.anyRequest()
	    	.authenticated()
        .and()
	        .formLogin()
	        .loginPage("/login")	//登录地址
	        .successHandler(webAuthenctiationSuccessHandler)//登陆成功处理
	        .failureHandler(webAuthenctiationFailureHandler)
	        .authenticationDetailsSource(webAuthenticationDetailsSource)
	//        .defaultSuccessUrl("/wdc/load")		//登录成功后的跳转地址
	        .permitAll()
        .and()
	        .logout()
	        .logoutSuccessUrl("/login")
	        .logoutSuccessHandler(webAuthenctiationLogoutSuccessHandler)
	        .permitAll()
        .and()
	        .rememberMe()//记住我
	        .rememberMeServices(rememberMeServices())
	        .authenticationSuccessHandler(rememberAuthenctiationSuccessHandler)
	        .tokenRepository(persistentTokenRepository())//设置持久化token
	        .key(INTERNAL_SECRET_KEY)
	        .tokenValiditySeconds(marvelConfig.getRememberDays() * 24 * 60 * 60); //记住登录1天(24小时 * 60分钟 * 60秒)*7天
    	
    	http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class);
    	http.addFilterBefore(webFilterAbstractSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
      * 设置不需要Security过滤的资源
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**")
			.antMatchers("/images/**")
			.antMatchers("/js/**")
			.antMatchers("/modules/**")
			.antMatchers("/error")
			.antMatchers("/brpc/**")
			.antMatchers("/vcc/code/image")
			.antMatchers("/druid/**");
	}


	/**
	 * 对象注入
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		
		try {
			auth.authenticationProvider(webAuthenticationProvider);
            auth.userDetailsService(rememberUserDetailsService);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * @Description 采用固化Token模式，会在数据库中产生表persistent_logins
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-29 22:35:54
	 */
	@Bean
    public PersistentTokenRepository persistentTokenRepository(){
		
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //数据源设置
        tokenRepository.setDataSource(dataSource);
        //启动的时候创建表，这里只执行一次，第二次就注释掉，否则每次启动都重新创建表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
	
	/**
	 * @Description 验证cookie的信息，做自动登录
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-29 22:36:53
	 */
	@Bean
    public RememberMeServices rememberMeServices() {
		
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 注入 UserDetailsSerice 实例
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(INTERNAL_SECRET_KEY, rememberUserDetailsService, tokenRepository);
        return rememberMeServices;
    }
	
}
