package com.dzb.console.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.dzb.console.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.console.config.MarvelConfig;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.extra.ftp.Ftp;

/**
 * @Description 文件操作服务类，用于统一文件的操作行为，隔离本地与服务器的操作差异。
 * @author 38840472@qq.com
 * @date 2020-06-17 21:18:04
 */
@Service
public class BaseFileService {
	
	private static final Logger log = LoggerFactory.getLogger(BaseFileService.class);
	
	@Autowired
	private MarvelConfig marvelConfig;

	/**
	 * @Description 写入文件
	 * @param path
	 * @param fileName
	 * @param bytes
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 21:17:51
	 */
	public boolean writer(String path, String fileName, byte[] bytes) {
		
		InputStream inputStream = new ByteArrayInputStream(bytes);
		if("local".equals(marvelConfig.getDocumentModel())) {
			File file = null;
			if(fileName.contains(Constants.MOBILE_WEB)){
				String moblieLocalPath = marvelConfig.getDocumentMoblieLocalPath();
				FileWriter writer = new FileWriter(moblieLocalPath + path + "/" + fileName);
				file = writer.writeFromStream(inputStream);
			}else {
				String loaclPath = marvelConfig.getDocumentLocalPath();
				FileWriter writer = new FileWriter(loaclPath + path + "/" + fileName);
				file = writer.writeFromStream(inputStream);
			}
			return null == file ? false : true;
		}else if("ftp".equals(marvelConfig.getDocumentModel())) {
			
			String serverPath = marvelConfig.getFtpServerPath();
			Ftp ftp = new Ftp(marvelConfig.getFtpIp(), marvelConfig.getFtpPort(), marvelConfig.getFtpUsername(), marvelConfig.getFtpPassword());
			boolean b = ftp.upload(serverPath + path, fileName, inputStream);
			try {
    			ftp.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			return b;
		}else {
			return false;
		}
	}
	
	/**
	 * @Description 读取文件
	 * @param path
	 * @param fileName
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 21:32:27
	 */
	public byte[] reader(String path, String fileName) {
		
		if("local".equals(marvelConfig.getDocumentModel())) {
			FileReader fileReader = null;
			if (fileName.contains(Constants.MOBILE_WEB)){
				String moblieLocalPath = marvelConfig.getDocumentMoblieLocalPath();
				log.info("读取移动端本地文件 路径："+ (moblieLocalPath + path) + " 文件名：" + fileName);
				fileReader = new FileReader(moblieLocalPath + path + "/" + fileName);
			}else {
				String loaclPath = marvelConfig.getDocumentLocalPath();
				log.info("读取本地文件 路径："+ (loaclPath + path) + " 文件名：" + fileName);
				fileReader = new FileReader(loaclPath + path + "/" + fileName);
			}
			return fileReader.readBytes();
		}else if("ftp".equals(marvelConfig.getDocumentModel())) {
			
			String serverPath = marvelConfig.getFtpServerPath();
			Ftp ftp = new Ftp(marvelConfig.getFtpIp(), marvelConfig.getFtpPort(), marvelConfig.getFtpUsername(), marvelConfig.getFtpPassword());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			log.info("读取服务器中的文件 路径："+ (serverPath + path) + " 文件名：" + fileName);
	    	ftp.download(serverPath + path, fileName, out);
	    	try {
    			ftp.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
	    	return out.toByteArray();
		}else {
			return null;
		}
	}
	
	/**
	 * @Description 删除文件
	 * @param path
	 * @param fileName
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 21:21:49
	 */
	public boolean delete(String path, String fileName) {
		
		fileName = null == fileName ? "" : fileName;
		if("local".equals(marvelConfig.getDocumentModel())) {
			
			String loaclPath = marvelConfig.getDocumentLocalPath();
			return FileUtil.del(loaclPath + path + fileName);
		}else if("ftp".equals(marvelConfig.getDocumentModel())) {
			
			String serverPath = marvelConfig.getFtpServerPath();
			Ftp ftp = new Ftp(marvelConfig.getFtpIp(), marvelConfig.getFtpPort(), marvelConfig.getFtpUsername(), marvelConfig.getFtpPassword());
			boolean b = ftp.delFile(serverPath + path + fileName);
			try {
    			ftp.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			return b;
		}else {
			return false;
		}
	}
}
