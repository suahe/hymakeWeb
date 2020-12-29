package com.dzb.wm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.MediaBase64Entity;
import com.dzb.console.entity.MediaFileEntity;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.service.BaseFileService;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.util.Base64Util;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.config.WebsiteConfig;
import com.dzb.wm.entity.NewsEntity;
import com.dzb.wm.mapper.NewsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_news 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-03 13:39:45
 * @version 1.0
 * @remark create by ca
 */

@Service("NewsService")
@Transactional
public class NewsService {

	private static final Logger log = LoggerFactory.getLogger(NewsService.class);
	
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private BaseTypeService baseTypeService;
	@Autowired
	private WebsiteConfig websiteConfig;
	@Autowired
	private BaseFileService baseFileService;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public PageInfo<NewsEntity> getPagination(Integer offset, Integer limit, NewsEntity newsEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		PageInfo<NewsEntity> pi = new PageInfo<NewsEntity>(newsMapper.selectPagination(newsEntity));
		List<NewsEntity> list = pi.getList();
		list = baseTypeService.translate(list, "FBZ", "author", "authorName");
		list = baseTypeService.translate(list, "XWLX", "newType", "newTypeName");
		pi.setList(list);
		return pi;
	}
	
	/**
	 * @Description 查询所有已经发布的新闻资讯列表
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:44:09
	 */
	public List<NewsEntity> getAllPushList(){
		
		List<NewsEntity> list = newsMapper.selectAllPushList();
		list = baseTypeService.translate(list, "FBZ", "author", "authorName");
		list = baseTypeService.translate(list, "XWLX", "newType", "newTypeName");
		return list;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public NewsEntity getByPkey(long newsId){
	
		log.debug("通过主键查询信息");
		NewsEntity newsEntity =  newsMapper.selectByPkey(newsId);
		newsEntity = baseTypeService.translate(newsEntity, "FBZ", "author", "authorName");
		newsEntity = baseTypeService.translate(newsEntity, "XWLX", "newType", "newTypeName");
		return newsEntity;
	}
	
	/**
	 * @Description 新增信息
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public int insert(NewsEntity newsEntity, String media) {

		log.debug("新增信息");
		long newId = IDUtil.getSnowflakeId();
		newsEntity.setNewsId(newId);
		newsEntity.setIsHeadline(0); 		//默认设置为不是头条新闻
		if(null != media && !"".equals(media)) {
			newsEntity.setMediaType(Base64Util.getMediaType(media));
			newsEntity.setExt(Base64Util.getExtension(media));
			String path = writeImage(String.valueOf(newId), media);
			newsEntity.setPath(path);
		}
		newsEntity.setUpdateUserid(UserInfoUtil.getUserId());
		newsEntity.setUpdateTime(new Date());
		return newsMapper.insert(newsEntity);
	}
	
	
	/**
	 * @Description 编辑信息
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public int update(NewsEntity newsEntity, String media){
	
		log.debug("编辑信息");
		long newId = newsEntity.getNewsId();
		newsEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		newsEntity.setUpdateTime(new Date());
	
		/**
 		 * 该功能需要前台界面通过none标志来判断
 		 * <input type="text" id="_media" name="media" value="none" style="display: none;" >
 		 */
 		if("none".equals(media)) {	//不进行多媒体文件的更新
 			return newsMapper.updateWithoutMedia(newsEntity);
 		}else if("".equals(media)) {	//删除多媒体文件
 			newsEntity.setMediaType(null);
 			newsEntity.setPath(null);
 			newsEntity.setExt(null);
 			return newsMapper.update(newsEntity);
 		}else {	//多媒体文件更新
 			newsEntity.setMediaType(Base64Util.getMediaType(media));
			newsEntity.setExt(Base64Util.getExtension(media));
			String path = writeImage(String.valueOf(newId), media);
			newsEntity.setPath(path);
			return newsMapper.update(newsEntity);
 		}
	}
	
	/**
	 * @Description 写入文件到服务器
	 * @param fileName
	 * @param base64String
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 11:18:12
	 */
	private String writeImage(String fileName, String base64String) {
		
		log.debug("--写入文件到服务器-- 开始");
		if (StringUtils.isBlank(base64String)) {
			log.debug("--写入文件到服务器-- 文件不存在");
			return "";
		}
		MediaBase64Entity mediaBase64Entity = Base64Util.convertToFile(base64String);
		byte[] bytes = mediaBase64Entity.getMedia();
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {
				bytes[i] += 256;
			}
		}
		String path = websiteConfig.getPathImage() + websiteConfig.getPathNewsImage();
		fileName = fileName + mediaBase64Entity.getExt();
		
		boolean b = baseFileService.writer(path, fileName, bytes);
		if(b) {
			log.debug("--写入文件到服务器-- 成功");
			return path + "/" + fileName;
		}else {
			log.debug("--写入文件到服务器-- 失败");
			return "";
		}
	}
	
	/**
	 * @Description 删除信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public int delete(long newsId){
	
		log.debug("删除信息");
		return newsMapper.delete(newsId);
	}
	
	/**
	 * @Description 发布新闻资讯
	 * @param newsId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:07:55
	 */
	public int updatePushTimeByNewsId(long newsId, Date pushTime) {
		
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setNewsId(newsId);
		newsEntity.setPushTime(pushTime);
		newsEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		newsEntity.setUpdateTime(new Date());
		return newsMapper.updatePushTimeByNewsId(newsEntity);
	}
	
	/**
	 * @Description 生成新闻资讯详细页面
	 * @param newsId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 15:21:59
	 */
	public boolean generateView(long newsId) {
		
		boolean b = false;
		return b;
	}
	
	/**
	 * @Description 更新头条状态
	 * @param newsId
	 * @param isHeadline
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-23 20:23:01
	 */
	public int updateIsHeadlineByNewsId(long newsId, int isHeadline) {
		
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setNewsId(newsId);
		newsEntity.setIsHeadline(isHeadline);
		newsEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		newsEntity.setUpdateTime(new Date());
		return newsMapper.updateIsHeadlineByNewsId(newsEntity);
	}
	
	/**
	 * @Description 查询头条的数量
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-23 20:44:12
	 */
	public int countByIsHeadline() {
		
		return newsMapper.countByIsHeadline();
	}
	
	public MediaFileEntity getHeadLineImage(long newsId) {
		
		NewsEntity newsEntity = newsMapper.selectByPkey(newsId);
		if(null == newsEntity) {
			return null;
		}else {
			if(null != newsEntity.getPath() && !"".equals(newsEntity.getPath())) {
				String path = newsEntity.getPath();
				String filePath = path.substring(0, path.lastIndexOf("/"));
				String fileName = path.substring(path.lastIndexOf("/")+1, path.length());
				byte[] bytes = baseFileService.reader(filePath, fileName);
				return new MediaFileEntity(MediaType.valueOf(newsEntity.getMediaType()), bytes);
			} else {
				return null;
			}
		}
	}
	
	
}