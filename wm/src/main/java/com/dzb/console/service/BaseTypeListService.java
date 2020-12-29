package com.dzb.console.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseTypeListEntity;
import com.dzb.console.mapper.BaseTypeListMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_base_type_list 表Service类
 * @author 38840472@qq.com
 * @date 2020-05-13 14:59:08
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseTypeListService")
@Transactional
public class BaseTypeListService {

	private static final Logger log = LoggerFactory.getLogger(BaseTypeListService.class);
	
	@Autowired
	private BaseTypeListMapper baseTypeListMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	public PageInfo<BaseTypeListEntity> getPagination(Integer offset, Integer limit, BaseTypeListEntity baseTypeListEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<BaseTypeListEntity>(baseTypeListMapper.selectPagination(baseTypeListEntity));
	}
	
	public List<BaseTypeListEntity> getListByBasetypeId(String basetypeId) {
		
		log.debug("查询信息列表");
		return baseTypeListMapper.selectListByBasetypeId(basetypeId);
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	public BaseTypeListEntity getByPkey(String baseTypeListId){
	
		log.debug("通过主键查询信息");
		return baseTypeListMapper.selectByPkey(baseTypeListId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseTypeId
	 * @param name
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 19:38:47
	 */
	public int insert(String baseTypeId, String name, String value) {

		log.debug("新增信息");
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		int max = getMaxByBaseTypeId(baseTypeId);
		baseTypeListEntity.setBaseTypeListId(IDUtil.getId());
		baseTypeListEntity.setBaseTypeId(baseTypeId);
		baseTypeListEntity.setName(name);
		baseTypeListEntity.setValue(value);
		baseTypeListEntity.setSequ(max + 1);
		baseTypeListEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseTypeListEntity.setCreateTime(new Date());
		baseTypeListEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseTypeListEntity.setUpdateTime(new Date());
		return baseTypeListMapper.insert(baseTypeListEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeListId
	 * @param name
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 19:50:45
	 */
	public int update(String baseTypeListId, String name, String value){
	
		log.debug("编辑信息");
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		baseTypeListEntity.setBaseTypeListId(baseTypeListId);
		baseTypeListEntity.setName(name);
		baseTypeListEntity.setValue(value);
		baseTypeListEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseTypeListEntity.setUpdateTime(new Date());
		return baseTypeListMapper.update(baseTypeListEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	public int delete(String baseTypeListId){
	
		log.debug("删除信息");
		return baseTypeListMapper.delete(baseTypeListId);
	}
	
	/**
	 * @Description 通过数据字典编号查询排序序列的最大值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 19:37:27
	 */
	public int getMaxByBaseTypeId(String baseTypeId) {
		
		Integer max = baseTypeListMapper.selectMaxByBaseTypeId(baseTypeId);
		return (null == max) ? 0 : max;
	}
	
	public BaseTypeListEntity getDownBySequAndBaseTypeId(String baseTypeId, int sequ) {
		
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		baseTypeListEntity.setBaseTypeId(baseTypeId);
		baseTypeListEntity.setSequ(sequ);
		return baseTypeListMapper.selectDownBySequAndBaseTypeId(baseTypeListEntity);
	}
	
	/**
	 * @Description 排序下移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int down(String baseTypeListId) {
		
		int i = 0;
		BaseTypeListEntity baseTypeListEntity = getByPkey(baseTypeListId);
		if(null != baseTypeListEntity) {
			int sequ = baseTypeListEntity.getSequ();
			String baseTypeId = baseTypeListEntity.getBaseTypeId();
			BaseTypeListEntity downBaseTypeListEntity = getDownBySequAndBaseTypeId(baseTypeId, sequ);
			if(null != downBaseTypeListEntity) {
				String downBaseTypeListId = downBaseTypeListEntity.getBaseTypeListId();
				int downSequ = downBaseTypeListEntity.getSequ();
				
				updateSequ(downBaseTypeListId, sequ);
				i = updateSequ(baseTypeListId, downSequ);
			}
		}
		return i;
	}
	
	public BaseTypeListEntity getUpBySequAndBaseTypeId(String baseTypeId, int sequ) {
		
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		baseTypeListEntity.setBaseTypeId(baseTypeId);
		baseTypeListEntity.setSequ(sequ);
		return baseTypeListMapper.selectUpBySequAndBaseTypeId(baseTypeListEntity);
	}
	
	/**
	 * @Description 排序上移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int up(String baseTypeListId) {
		
		int i = 0;
		BaseTypeListEntity baseTypeListEntity = getByPkey(baseTypeListId);
		if(null != baseTypeListEntity) {
			int sequ = baseTypeListEntity.getSequ();
			String baseTypeId = baseTypeListEntity.getBaseTypeId();
			BaseTypeListEntity upBaseTypeListEntity = getUpBySequAndBaseTypeId(baseTypeId, sequ);
			if(null != upBaseTypeListEntity) {
				String upBaseTypeListId = upBaseTypeListEntity.getBaseTypeListId();
				int upSequ = upBaseTypeListEntity.getSequ();
				
				updateSequ(upBaseTypeListId, sequ);
				i = updateSequ(baseTypeListId, upSequ);
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
	public int updateSequ(String baseTypeListId, int sequ) {
		BaseTypeListEntity baseTypeListEntity = new BaseTypeListEntity();
		baseTypeListEntity.setBaseTypeListId(baseTypeListId);
		baseTypeListEntity.setSequ(sequ);
		baseTypeListEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseTypeListEntity.setUpdateTime(new Date());
 		return baseTypeListMapper.updateSequ(baseTypeListEntity);
	}
	
}