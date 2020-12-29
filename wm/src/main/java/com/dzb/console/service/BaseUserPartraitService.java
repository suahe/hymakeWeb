package com.dzb.console.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dzb.console.config.MarvelConfig;
import com.dzb.console.entity.BaseUserPartraitEntity;
import com.dzb.console.entity.MediaFileEntity;
import com.dzb.console.mapper.BaseUserPartraitMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.BaseUtil;
import com.dzb.console.util.IDUtil;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ObjectUtil;

/**
 * @Description t_base_user_partrait 表Service类
 * @author 38840472@qq.com
 * @date 2020-04-23 21:13:27
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseUserPartraitService")
@Transactional
public class BaseUserPartraitService {

	@Autowired
	private BaseUserPartraitMapper baseUserPartraitMapper;
	@Autowired
	private MarvelConfig marvelConfig;

	/**
	 * @Description 新增信息
	 * @param baseUserPartraitEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 21:13:27
	 */
	public int insert(BaseUserPartraitEntity baseUserPartraitEntity) {

		return baseUserPartraitMapper.insert(baseUserPartraitEntity);
	}

	/**
	 * @Description 编辑信息
	 * @param baseUserPartraitEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 21:13:27
	 */
	public int update(BaseUserPartraitEntity baseUserPartraitEntity) {

		return baseUserPartraitMapper.update(baseUserPartraitEntity);
	}

	/**
	 * @Description 把头像文件写入磁盘
	 * @param userPortraitId
	 * @param images
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-02 20:01:32
	 */
	public String fileWriter(long userPortraitId, MultipartFile images) {

		String path = null;
		if (ObjectUtil.isNotNull(images)) {
			String originalFilename = images.getOriginalFilename();
			String extension = ".jpeg";
			if (ObjectUtil.isNotNull(originalFilename)) {
				extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			}
			String fileName = "/" + userPortraitId + extension;
			String resourceUrl = BaseUtil.getResourceUrl();
			String portraitPath = marvelConfig.getPortraitPath();
			FileWriter writer = new FileWriter(resourceUrl + portraitPath + fileName);
			InputStream inputStream = null;
			try {
				inputStream = new ByteArrayInputStream(images.getBytes());
				File file = writer.writeFromStream(inputStream);
				if (ObjectUtil.isNotNull(file)) {
					path = fileName;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	public byte[] fileReader(String fileName) {

		String resourceUrl = BaseUtil.getResourceUrl();
		String portraitPath = marvelConfig.getPortraitPath();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(resourceUrl + portraitPath + fileName);
		}catch (Exception e) {
			return null;
		}
		return fileReader.readBytes();
	}

	/**
	 * @Description 保存头像文件到数据库
	 * @param userId
	 * @param images
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-24 15:36:44
	 */
	public int save(String userId, MultipartFile images) {

		int i = 0;
		BaseUserPartraitEntity baseUserPartraitEntity = baseUserPartraitMapper.selectUserPortraitIdByUserId(userId);
		if (ObjectUtil.isNull(baseUserPartraitEntity)) {
			long userPortraitId = IDUtil.getSnowflakeId();
			String fileName = fileWriter(userPortraitId, images);
			if (ObjectUtil.isNotNull(fileName)) {
				baseUserPartraitEntity = new BaseUserPartraitEntity();
				baseUserPartraitEntity.setUserId(userId);
				baseUserPartraitEntity.setMediaType(images.getContentType());
				baseUserPartraitEntity.setPath(fileName);
				baseUserPartraitEntity.setUserPortraitId(userPortraitId);
				baseUserPartraitEntity.setCreateTime(new Date());
				baseUserPartraitEntity.setCreateUserid(UserInfoUtil.getUserId());
				baseUserPartraitEntity.setUpdateTime(new Date());
				baseUserPartraitEntity.setUpdateUserid(UserInfoUtil.getUserId());
				i = insert(baseUserPartraitEntity);
			}
		} else {
			String fileName = fileWriter(baseUserPartraitEntity.getUserPortraitId(), images);
			if (ObjectUtil.isNotNull(fileName)) {
				baseUserPartraitEntity.setMediaType(images.getContentType());
				baseUserPartraitEntity.setPath(fileName);
				baseUserPartraitEntity.setUpdateTime(new Date());
				baseUserPartraitEntity.setUpdateUserid(UserInfoUtil.getUserId());
				i = update(baseUserPartraitEntity);
			}
		}
		return i;
	}

	/**
	 * @Description 通过用户编号从磁盘读取用户头像文件
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-02 20:19:53
	 */
	public MediaFileEntity load(String userId) {

		byte[] bytes = null;
		BaseUserPartraitEntity baseUserPartraitEntity = baseUserPartraitMapper.selectUserPortraitIdByUserId(userId);
		if (ObjectUtil.isNotNull(baseUserPartraitEntity)) {
			bytes = fileReader(baseUserPartraitEntity.getPath());
			if(null != bytes) {
				return new MediaFileEntity(MediaType.valueOf(baseUserPartraitEntity.getMediaType()), bytes);
			}
		}
		return new MediaFileEntity(MediaType.valueOf("image/jpeg"), fileReader("/partrait_default.jpg"));

	}
	
	/**
	 * @Description 从磁盘读取当前用户头像文件
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-02 20:32:00
	 */
	public MediaFileEntity loadByActiveUser() {

		String userId = UserInfoUtil.getUserId();
		return load(userId);
	}

}