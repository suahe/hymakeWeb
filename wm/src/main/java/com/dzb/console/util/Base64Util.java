package com.dzb.console.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.dzb.console.entity.MediaBase64Entity;

public class Base64Util {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static{
		map.put("image/jpeg", ".jpeg");
		map.put("image/png", ".png");
		map.put("image/gif", ".gif");
		map.put("video/mp4", ".mp4");
	}

	public static MediaBase64Entity convertToFile(String base64String) {
		
		String mediaType = getMediaType(base64String);
		String ext = map.get(mediaType);
		String body = getBody(base64String);
		byte[] media = Base64.decodeBase64(body);
		return new MediaBase64Entity(mediaType, ext, media, body);
	}
	
	public static String getMediaType(String base64String) {
		
		String[] baseStrArray = base64String.split(",");
		String header = baseStrArray[0];
		header = header.replace("data:", "").replace(";base64", "");
		return header;
	}
	
	public static String getBody(String base64String) {
		
		String[] baseStrArray = base64String.split(",");
		return baseStrArray[1];
	}
	
	public static String getExtension(String base64String) {
		
		String mediaType = getMediaType(base64String);
		if(null == mediaType) {
			return "未知";
		}else {
			return map.get(mediaType);
		}
		
	}
}
