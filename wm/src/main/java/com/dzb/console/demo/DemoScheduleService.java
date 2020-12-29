package com.dzb.console.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 定时器Demo
 * @author 38840472@qq.com
 * @date 2020-04-24 08:51:28
 */

@Component
@Configurable
@EnableScheduling
public class DemoScheduleService {

	
	/**
	 * @Description 每10分钟执行一次
	 * @author 38840472@qq.com
	 * @date 2020-04-22 11:18:39
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
    public void runing(){

    }

}

