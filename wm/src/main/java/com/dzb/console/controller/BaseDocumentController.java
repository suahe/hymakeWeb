package com.dzb.console.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzb.console.entity.BaseDocumentEntity;
import com.dzb.console.entity.BaseFileEntity;
import com.dzb.console.service.BaseDocumentService;
import com.dzb.console.syslog.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_document 表Controller类
 * @author 38840472@qq.com
 * @date 2020-05-08 14:21:58
 * @version 1.0
 * @remark create by ca
 */


@Controller
@RequestMapping("bdmc")
public class BaseDocumentController{
	
	private static final Logger log = LoggerFactory.getLogger(BaseDocumentController.class);

	@Autowired
	private BaseDocumentService baseDocumentService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
  	@SysLog(moduleName = "#", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load() {
		
		log.debug("初始化列表页面");
		return "console/t_base_document_load";
	}
  	
  	/**
	 * @Description 初始化Demo页面
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
  	@SysLog(moduleName = "#", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/demo",method = RequestMethod.GET)
	public String demo() {
		
		log.debug("初始化Demo页面");
		return "demo/t_base_document_load";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	@SysLog(moduleName = "#", submoduleName = "查询列表", params = {"offset","limit","baseDocumentEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<BaseDocumentEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit, BaseDocumentEntity baseDocumentEntity) {
		
		return baseDocumentService.getPagination(offset, limit ,baseDocumentEntity);
	}
	
	/**
	 * @Description 初始化编辑信息
	 * @param documentId
	 * @param model
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	@SysLog(moduleName = "#", submoduleName = "初始化查询信息", params = {"documentId"})
	@RequestMapping(value = "/loadView",method = RequestMethod.GET)
	@ResponseBody
	public BaseDocumentEntity loadView(@RequestParam("documentId") String documentId,Model model) {
		
		return baseDocumentService.getByDocumentId(documentId);
	}
	
	/**
	 * @Description 删除信息
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	@SysLog(moduleName = "#", submoduleName = "删除信息", params = {"documentId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("documentId") String documentId) {
		
		return baseDocumentService.delete(documentId);
	}
	
	/**
	 * @Description 文件上传
	 * @param file
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-11 14:37:59
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
	
		return baseDocumentService.uploadFile(file);
	}
	
	/**
	 * @Description 下载文件
	 * @param response
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-11 14:34:31
	 */
	@GetMapping(value= "/download")
    public void download(HttpServletResponse response, @RequestParam("documentId") String documentId){
        
		BaseFileEntity baseFileEntity = baseDocumentService.download(documentId);
		if(null != baseFileEntity) {
			
			/** 设置文件字符集编码，防止现在文件乱码 */
			String charset = "UTF-8";
			String fileName = baseFileEntity.getName();
			String encode = null;
			
			try {
				encode = URLEncoder.encode(fileName, charset);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			response.setCharacterEncoding(charset);
			/** 设置为下载而不是自动打开 */
			response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment;filename=" + encode + ";filename*=" + charset + "''" + encode);
	        
	        try {
				OutputStream os = response.getOutputStream();
				os.write(baseFileEntity.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
}