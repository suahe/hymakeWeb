package com.dzb.wm.service;

import java.util.Date;
import java.util.List;

import com.dzb.console.constant.Constants;
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
import com.dzb.wm.entity.PosterItemEntity;
import com.dzb.wm.mapper.PosterItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.codec.Base64;

/**
 * @Description t_poster_image 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-15 09:19:15
 * @version 1.0
 * @remark create by ca
 */

@Service("PosterItemService")
@Transactional
public class PosterItemService {

	private static final Logger log = LoggerFactory.getLogger(PosterItemService.class);
	@Autowired
	private PosterItemMapper posterItemMapper;
	@Autowired
	private WebsiteConfig websiteConfig;
	@Autowired
	private BaseTypeService baseTypeService;
	@Autowired
	private BaseFileService baseFileService;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param posterItemEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	public PageInfo<PosterItemEntity> getPagination(Integer offset, Integer limit, PosterItemEntity posterItemEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		PageInfo<PosterItemEntity> pi = new PageInfo<PosterItemEntity>(posterItemMapper.selectPagination(posterItemEntity));
		List<PosterItemEntity> list = pi.getList();
		list = baseTypeService.translate(list, "GGLX", "type", "typeName");
		list = baseTypeService.translate(list, "TZFS", "isTarget", "isTargetName");
		pi.setList(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param posterId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	public PosterItemEntity getByPkey(long posterItemId){
	
		log.debug("通过主键查询信息");
		PosterItemEntity posterItemEntity = posterItemMapper.selectByPkey(posterItemId);
		posterItemEntity = baseTypeService.translate(posterItemEntity, "GGLX", "type", "typeName");
		posterItemEntity = baseTypeService.translate(posterItemEntity, "TZFS", "isTarget", "isTargetName");
		return posterItemEntity;
	}
	
	/**
	 * @Description 保存信息
	 * @param posterItemId
	 * @param posterId
	 * @param name
	 * @param url
	 * @param isTarget
	 * @param media
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 11:07:37
	 */
	public int save(long posterItemId, String type,  String name, String url, String mobileUrl,
					String isTarget, String media, String mobileMedia) {
		
		log.debug("保存信息");
		if(0 == posterItemId) {
			return insert(type, name, url, mobileUrl,isTarget, media,mobileMedia);
		}else {
			return update(posterItemId, type, name, url,mobileUrl, isTarget, media,mobileMedia);
		}
	}
	
	/**
	 * @Description 新增信息
	 * @param posterId
	 * @param name
	 * @param url
	 * @param isTarget
	 * @param media
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 11:07:22
	 */
	public int insert(String type, String name, String url,String mobileUrl,
					  String isTarget, String media, String mobileMedia) {

		log.debug("新增信息");
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		long posterItemId = IDUtil.getSnowflakeId();
		posterItemEntity.setPosterItemId(posterItemId);
		posterItemEntity.setType(type);
		posterItemEntity.setName(name);
		posterItemEntity.setUrl(url);
		posterItemEntity.setMobileUrl(mobileUrl);
		posterItemEntity.setIsTarget(isTarget);
		posterItemEntity.setPushTime(null);
		Integer max = getMaxSequ();
		posterItemEntity.setSequ(max + 1);
		posterItemEntity.setUpdateUserid(UserInfoUtil.getUserId());
		posterItemEntity.setUpdateTime(new Date());
		if(null != media && !"".equals(media)) {
			posterItemEntity.setMediaType(Base64Util.getMediaType(media));
			String path = writeMediaToFile(String.valueOf(posterItemId), type, media);
			posterItemEntity.setPath(path);
		}
		if(null != mobileMedia && !"".equals(mobileMedia)) {
			posterItemEntity.setMobileMediaType(Base64Util.getMediaType(mobileMedia));
			String picName = Constants.MOBILE_WEB+"_"+String.valueOf(posterItemId);
			String mobliePath = writeMediaToFile(picName, type, mobileMedia);
			posterItemEntity.setMobilePath(mobliePath);
		}
		return posterItemMapper.insert(posterItemEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param posterItemId
	 * @param name
	 * @param url
	 * @param isTarget
	 * @param media
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 11:06:42
	 */
	public int update(long posterItemId, String type, String name, String url, String mobileUrl,
					  String isTarget, String media, String mobileMedia){
		
		log.debug("编辑信息");
		//PosterItemEntity posterItemEntity = new PosterItemEntity();
		PosterItemEntity posterItemEntity = getByPkey(posterItemId);
		if(posterItemEntity==null){
			return 0;
		}
		posterItemEntity.setPosterItemId(posterItemId);
		posterItemEntity.setType(type);
		posterItemEntity.setName(name);
		posterItemEntity.setUrl(url);
		posterItemEntity.setMobileUrl(mobileUrl);
		posterItemEntity.setIsTarget(isTarget);
		posterItemEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		posterItemEntity.setUpdateTime(new Date());
 		
 		/**
 		 * 该功能需要前台界面通过none标志来判断
 		 * <input type="text" id="_media" name="media" value="none" style="display: none;" >
 		 */
 		if("none".equals(media)) {	//不进行多媒体文件的更新
			//不进行设置电脑端轮播文件路径
 		}else if("".equals(media)) {	//删除多媒体文件
 			posterItemEntity.setMediaType("");
 			posterItemEntity.setPath("");
 		}else {	//多媒体文件更新
 			posterItemEntity.setMediaType(Base64Util.getMediaType(media));
			String path = writeMediaToFile(String.valueOf(posterItemId), type, media);
			posterItemEntity.setPath(path);
 		}

		if("none".equals(mobileMedia)) {	//不进行多媒体文件的更新
			//不进行设置手机端轮播文件路径
		}else if("".equals(mobileMedia)) {	//删除多媒体文件
			posterItemEntity.setMobileMediaType("");
			posterItemEntity.setMobilePath("");
		}else {	//多媒体文件更新
			posterItemEntity.setMobileMediaType(Base64Util.getMediaType(mobileMedia));
			String picName = Constants.MOBILE_WEB+"_"+String.valueOf(posterItemId);
			String mobliePath = writeMediaToFile(picName, type, mobileMedia);
			posterItemEntity.setMobilePath(mobliePath);
		}
		return posterItemMapper.update(posterItemEntity);
	}
	
	/**
	 * @Description 写入文件到Ftp服务器
	 * @param fileName
	 * @param type
	 * @param base64String
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 11:18:12
	 */
	private String writeMediaToFile(String fileName, String type, String base64String) {
		
		log.debug("写入文件到服务器磁盘");
		if (StringUtils.isBlank(base64String)) {
			return "";
		}
		MediaBase64Entity mediaBase64Entity = Base64Util.convertToFile(base64String);
		byte[] bytes = mediaBase64Entity.getMedia();
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {
				bytes[i] += 256;
			}
		}
		
		String path = "";
		if("TP".equals(type)) {
			path = websiteConfig.getPathImage();
		}
		if("SP".equals(type)) {
			path = websiteConfig.getPathVideo();
		}
		fileName = fileName + mediaBase64Entity.getExt();
		boolean b = baseFileService.writer(path, fileName, bytes);
		if(b) {
			return path + "/" + fileName;
		}else {
			return "";
		}
		 
	}
	
	public int getMaxSequ() {
		
		Integer max = posterItemMapper.selectMaxSequ();
		return null == max ? 0 : max;
	}
	
	/**
	 * @Description 删除信息
	 * @param posterId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	public int delete(long posterItemId){
	
		log.debug("删除信息");
		return posterItemMapper.delete(posterItemId);
	}
	
	public PosterItemEntity getDownBySequ(int sequ) {
		
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setSequ(sequ);
		return posterItemMapper.selectDownBySequAndPosterId(posterItemEntity);
	}
	
	/**
	 * @Description 排序下移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int down(long posterItemId) {
		
		int i = 0;
		PosterItemEntity posterItemEntity = getByPkey(posterItemId);
		if(null != posterItemEntity) {
			int sequ = posterItemEntity.getSequ();
			PosterItemEntity downPosterItemEntity = getDownBySequ(sequ);
			if(null != downPosterItemEntity) {
				long downPosterItemId = downPosterItemEntity.getPosterItemId();
				int downSequ = downPosterItemEntity.getSequ();
				updateSequ(downPosterItemId, sequ);
				i = updateSequ(posterItemId, downSequ);
			}
		}
		return i;
	}
	
	public PosterItemEntity getUpBySequ(int sequ) {
		
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setSequ(sequ);
		return posterItemMapper.selectUpBySequAndPosterId(posterItemEntity);
	}
	
	/**
	 * @Description 排序上移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int up(long posterItemId) {
		
		int i = 0;
		PosterItemEntity posterItemEntity = getByPkey(posterItemId);
		if(null != posterItemEntity) {
			int sequ = posterItemEntity.getSequ();
			PosterItemEntity upPosterItemEntity = getUpBySequ(sequ);
			if(null != upPosterItemEntity) {
				long upPosterItemId = upPosterItemEntity.getPosterItemId();
				int upSequ = upPosterItemEntity.getSequ();
				updateSequ(upPosterItemId, sequ);
				i = updateSequ(posterItemId, upSequ);
			}
		}
		return i;
	}
	
	/**
	 * @Description 更新排序序号
	 * @param baseTypeListId
	 * @param sequ
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:24:12
	 */
	public int updateSequ(long posterItemId, int sequ) {
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setPosterItemId(posterItemId);
		posterItemEntity.setSequ(sequ);
		posterItemEntity.setUpdateUserid(UserInfoUtil.getUserId());
		posterItemEntity.setUpdateTime(new Date());
 		return posterItemMapper.updateSequ(posterItemEntity);
	}
	
	
	/**
	 * @Description 发布/下架公告信息
	 * @param posterItemId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:07:55
	 */
	public int updatePushTimeByPosterItemId(long posterItemId, Date pushTime) {
		
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setPosterItemId(posterItemId);
		posterItemEntity.setPushTime(pushTime);
		posterItemEntity.setUpdateUserid(UserInfoUtil.getUserId());
		posterItemEntity.setUpdateTime(new Date());
		return posterItemMapper.updatePushTimeByPosterItemId(posterItemEntity);
	}
	
	public String imageToBase64ByPosterItemId(long posterItemId) {
		
		PosterItemEntity posterItemEntity = posterItemMapper.selectByPkey(posterItemId);
		if(null == posterItemEntity) {
			return null;
		}else {
			if(null != posterItemEntity.getPath()) {
				String path = posterItemEntity.getPath();
				String filePath = path.substring(0, path.lastIndexOf("/"));
				String fileName = path.substring(path.lastIndexOf("/")+1, path.length());
				byte[] bytes = baseFileService.reader(filePath, fileName);
				return Base64.encode(bytes);
			} else {
				return null;
			}
		}
	}
	
	public MediaFileEntity posterItemId(long posterItemId,String mark) {
		
		PosterItemEntity posterItemEntity = posterItemMapper.selectByPkey(posterItemId);
		if(null == posterItemEntity) {
			return null;
		}else {
			if(Constants.MOBILE_WEB.equals(mark)){
				if(null != posterItemEntity.getMobilePath() && !"".equals(posterItemEntity.getMobilePath())) {
					String mobilePath = posterItemEntity.getMobilePath();
					String filePath = mobilePath.substring(0, mobilePath.lastIndexOf("/"));
					String fileName = mobilePath.substring(mobilePath.lastIndexOf("/")+1, mobilePath.length());
					byte[] bytes = baseFileService.reader(filePath, fileName);
					return new MediaFileEntity(MediaType.valueOf(posterItemEntity.getMobileMediaType()), bytes);
				} else {
					return null;
				}
			}else {
				if(null != posterItemEntity.getPath() && !"".equals(posterItemEntity.getPath())) {
					String path = posterItemEntity.getPath();
					String filePath = path.substring(0, path.lastIndexOf("/"));
					String fileName = path.substring(path.lastIndexOf("/")+1, path.length());
					byte[] bytes = baseFileService.reader(filePath, fileName);
					return new MediaFileEntity(MediaType.valueOf(posterItemEntity.getMediaType()), bytes);
				} else {
					return null;
				}
			}

		}
	}
	
	/**
	 * @Description 查询有效的数据列表
	 * @param type
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 15:18:41
	 */
	public List<PosterItemEntity> getByValid(String type){
		
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setType(type);
		return posterItemMapper.selectByValid(posterItemEntity);
	}
	
	/**
	 * @Description 查找公告类型数量
	 * @param type
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-25 01:23:56
	 */
	public int countByType(String type) {
		
		PosterItemEntity posterItemEntity = new PosterItemEntity();
		posterItemEntity.setType(type);
		return posterItemMapper.countByType(posterItemEntity);
	}
	
}