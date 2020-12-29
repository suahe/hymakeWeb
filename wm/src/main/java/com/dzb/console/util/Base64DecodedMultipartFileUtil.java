package com.dzb.console.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description BASE64转MultipartFile工具类
 * @author 38840472@qq.com
 * @date 2020-04-24 15:35:14
 */
public class Base64DecodedMultipartFileUtil implements MultipartFile {

	private final byte[] imgContent;
	private final String header;

	public Base64DecodedMultipartFileUtil(byte[] imgContent, String header) {
		
	    this.imgContent = imgContent;
	    this.header = header.split(";")[0];
	  }

	@Override
	public String getName() {
		return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
	}

	@Override
	public String getOriginalFilename() {
		return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
	}

	@Override
	public String getContentType() {
		return header.split(":")[1];
	}

	@Override
	public boolean isEmpty() {
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(imgContent);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
			fileOutputStream.write(imgContent);
		}
	}

	/**
	 * @Description 把base64转MultipartFile
	 * @param base64
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-24 15:34:49
	 */
	public static MultipartFile base64Convert(String base64) {

		String[] baseStrs = base64.split(",");		
		byte[] b = Base64.decodeBase64(new String(baseStrs[1]).getBytes());
		return new Base64DecodedMultipartFileUtil(b, baseStrs[0]);
	}
}