package com.hymake.mobileWeb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import cn.hutool.core.util.IdUtil;

/**
 * @Description UUID生成工具类
 * @author daizb@hymake.com
 * @date 2018-03-04 19:42:35
 */
public class IDUtil {

	/**
	 * @Description 产生一个唯一的字符串编号
	 * @return
	 * @author daizb@hymake.com
	 * @date 2018-03-13 11:35:55
	 */
	public static String getId(){
		
		return IdUtil.fastSimpleUUID();
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
