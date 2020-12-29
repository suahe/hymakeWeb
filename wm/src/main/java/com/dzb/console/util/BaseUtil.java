package com.dzb.console.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

/**
 * @Description 基础工具类
 * @author 38840472@qq.com
 * @date 2018-09-28 10:22:20
 */
public class BaseUtil {
	
	/**
	 * @Description 获取部署的根目录
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-10-24 09:48:16
	 */
	public static String getResourceUrl() {
		
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!path.exists()) {
			path = new File("");
		}
		return path.getAbsolutePath()+ File.separator;
	}

}
