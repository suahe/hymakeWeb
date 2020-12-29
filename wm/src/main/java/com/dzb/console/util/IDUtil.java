package com.dzb.console.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.hutool.core.util.IdUtil;

/**
 * @Description UUID生成工具类
 * @author 38840472@qq.com
 * @date 2018-03-04 19:42:35
 */
public class IDUtil {

	/**
	 * @Description 产生一个唯一的字符串编号
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-03-13 11:35:55
	 */
	public static String getId(){
		
		return IdUtil.fastSimpleUUID();
	}
	
	/**
	 * @Description Snowflake算法单机版生成ID
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-25 01:39:08
	 */
	public static long getSnowflakeId() {
		
		return IdUtil.getSnowflake(1,1).nextId();
	}
	
	/**
	 * 产生一个唯一的字符串年月日时分秒毫秒+5位随机数
	 * @return
	 */
	public static String getUnique() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = sf.format(new Date()) + nextInt(10000,99999);
		return str;
	}
	
	private static int nextInt(final int min, final int max){
		
		Random rand= new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
	
}
