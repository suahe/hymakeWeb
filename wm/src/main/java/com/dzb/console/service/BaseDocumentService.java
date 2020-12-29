package com.dzb.console.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dzb.console.config.MarvelConfig;
import com.dzb.console.entity.BaseDocumentEntity;
import com.dzb.console.entity.BaseFileEntity;
import com.dzb.console.mapper.BaseDocumentMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @Description t_base_document 表Service类
 * @author 38840472@qq.com
 * @date 2020-05-08 14:21:58
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseDocumentService")
@Transactional
public class BaseDocumentService {
	
	private static final Logger log = LoggerFactory.getLogger(BaseDocumentService.class);
	
	@Autowired
	private MarvelConfig marvelConfig;
	
	@Autowired
	private BaseDocumentMapper baseDocumentMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	public PageInfo<BaseDocumentEntity> getPagination(Integer offset, Integer limit, BaseDocumentEntity baseDocumentEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<BaseDocumentEntity>(baseDocumentMapper.selectPagination(baseDocumentEntity));
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	public BaseDocumentEntity getByDocumentId(String documentId){
	
		log.debug("通过主键查询信息");
		return baseDocumentMapper.selectByPkey(documentId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	public String insert(BaseDocumentEntity baseDocumentEntity) {

		log.debug("新增信息");
		String id = IDUtil.getId();
		baseDocumentEntity.setDocumentId(id);
		baseDocumentEntity.setIsDel(0);
		baseDocumentEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseDocumentEntity.setCreateTime(new Date());
		baseDocumentEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseDocumentEntity.setUpdateTime(new Date());
		int i = baseDocumentMapper.insert(baseDocumentEntity);
		return i == 1 ? id : null;
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	public int update(BaseDocumentEntity baseDocumentEntity){
	
		log.debug("编辑信息");
		baseDocumentEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseDocumentEntity.setUpdateTime(new Date());
		return baseDocumentMapper.update(baseDocumentEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	public int delete(String documentId){
	
		log.debug("删除信息 documentId:"+documentId);
		int i = 0;
		BaseDocumentEntity baseDocumentEntity = getByDocumentId(documentId);
		if(null != baseDocumentEntity) {
			/* 获取yml文件中配置的本地路径  */
			String dirPath = marvelConfig.getDocumentLocalPath();
			/* 获取文件的全路径  */
			String localFilePath = baseDocumentEntity.getPath();
			boolean b = FileUtil.del(dirPath + File.separator + localFilePath);
			if(b) {
				i = baseDocumentMapper.delete(documentId);
			}
		}
		return i;
	}
	
	/**
	 * @Description 新增信息
	 * @param name
	 * @param contentType
	 * @param path
	 * @param md5key
	 * @param size
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-09 17:49:58
	 */
	public String insert(String name, String contentType, String path, String md5key, long size) {

		log.debug("新增信息");
		BaseDocumentEntity baseDocumentEntity = new BaseDocumentEntity();
		baseDocumentEntity.setName(name);
		baseDocumentEntity.setExt(contentType);
		baseDocumentEntity.setPath(path);
		baseDocumentEntity.setMd5key(md5key);
		baseDocumentEntity.setSize(size);
		return insert(baseDocumentEntity);
	}
	
	/**
	 * @Description 保存文件到本地
	 * @param multipartFile
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-09 17:38:17
	 */
	public String uploadFile(MultipartFile multipartFile){
		
		/* 生成文件的MD5摘要 */
		Digester md5 = new Digester(DigestAlgorithm.MD5);
        String md5key = "-";
		try {
			md5key = md5.digestHex(multipartFile.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String documentId = selectByMd5(md5key);
		if(documentId == null) {
			/* 生成当前年月的文件夹 */
			String folder = DateUtil.format(new Date(), "yyyyMM");
			/* 获取yml文件中配置的本地路径  */
			String dirPath = marvelConfig.getDocumentLocalPath();
			/* 获取上传文件的文件名 */
	        String fileName = multipartFile.getOriginalFilename();
	        /* 获取上传文件的文件类型 */
	        String contentType = multipartFile.getContentType();
	        /* 获取上传文件的大小 */
	        long size = multipartFile.getSize();
	        /* 获取上传文件的扩展名 */
	        String fileSuffix = FileUtil.extName(fileName);
	        /* 生成本地文件的名称 规则：雪花模型（19位）+扩展名 */
	        String localFileName = IdUtil.createSnowflake(0, 0).nextId() + "." + fileSuffix;
	        /* 生成本地文件路径 */
	        String filePath = File.separator + folder + File.separator + localFileName;
	        String fullFilePath = dirPath + filePath;
	        /* 根据路径生成本地文件 没有文件夹会自动创建 */
	        File localFile = FileUtil.touch(fullFilePath);
	        /* 写入数据到文件中 */
	        try {
				multipartFile.transferTo(localFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        log.info("新增文件");
	        return insert(fileName, contentType, filePath, md5key, size);
		} else {
			log.info("文件已存在");
			return  documentId;
		}
		
    }
	
	/**
	 * @Description 通过documentId，获取下载文件
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-11 14:48:31
	 */
	public BaseFileEntity download(String documentId) {
		
		BaseDocumentEntity baseDocumentEntity = getByDocumentId(documentId);
		
		if(null != baseDocumentEntity) {
			
			/* 获取yml文件中配置的本地路径  */
			String dirPath = marvelConfig.getDocumentLocalPath();
			/* 获取文件路径  */
			String localFileName = dirPath + baseDocumentEntity.getPath();
			
			if(FileUtil.exist(localFileName)) {
				
				byte[] bytes = null;
				try {
					bytes = FileUtil.readBytes(localFileName);
				} catch (IORuntimeException e) {
					e.printStackTrace();
				} 
				return new BaseFileEntity(documentId, baseDocumentEntity.getName(), baseDocumentEntity.getExt(), bytes);
			}else {
				return new BaseFileEntity();
			}
		} else {
			return new BaseFileEntity();
		}
		
	}
	
	/**
	 * @Description 通过Md5查找文件信息
	 * @param md5key
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 13:38:57
	 */
	public String selectByMd5(String md5key) {
		
		return baseDocumentMapper.selectByMd5(md5key);
	}
	
}