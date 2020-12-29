package com.dzb.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.entity.BaseUserRoleMappingEntity;
import com.dzb.console.mapper.BaseUserRoleMappingMapper;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Description t_base_user_role_mapping 表Service类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:21
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseUserRoleMappingService")
@Transactional
public class BaseUserRoleMappingService {
	
	@Autowired
	private BaseUserRoleMappingMapper baseUserRoleMappingMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public PageInfo<BaseUserRoleMappingEntity> selectPagination(Integer page, Integer rows, BaseUserRoleMappingEntity baseUserRoleMappingEntity) {
		
		PageHelper.startPage(page, rows);
		//PageHelper.orderBy("UPDATE_TIME DESC");
		List<BaseUserRoleMappingEntity> list = baseUserRoleMappingMapper.selectPagination(baseUserRoleMappingEntity);
		PageInfo<BaseUserRoleMappingEntity> pi = new PageInfo<BaseUserRoleMappingEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param userRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public BaseUserRoleMappingEntity getByPkey(String userRoleMappingId){
	
		return baseUserRoleMappingMapper.selectByPkey(userRoleMappingId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public int insert(BaseUserRoleMappingEntity baseUserRoleMappingEntity) {
		
		return baseUserRoleMappingMapper.insert(baseUserRoleMappingEntity);
	}
	
	public boolean insertRoleIdAndUserIds(String roleId,String[] userIds) {
		
		int i = 0;
		BaseUserRoleMappingEntity baseUserRoleMappingEntity = new BaseUserRoleMappingEntity();
		baseUserRoleMappingEntity.setRoleId(roleId);
		for(int j=0; j<userIds.length; j++) {
			baseUserRoleMappingEntity.setUserRoleMappingId(IDUtil.getId());
			baseUserRoleMappingEntity.setUserId(userIds[j]);
			i  = baseUserRoleMappingMapper.insert(baseUserRoleMappingEntity);
		}
		return i > 0 ? true : false;
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public int update(BaseUserRoleMappingEntity baseUserRoleMappingEntity){
	
		return baseUserRoleMappingMapper.update(baseUserRoleMappingEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param userRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public int delete(String userRoleMappingId){
	
		return baseUserRoleMappingMapper.delete(userRoleMappingId);
	}
	
	/**
	 * @Description 通过用户编号查找用户角色
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public List<String> getByUserId(String userId){
		
		return baseUserRoleMappingMapper.selectByUserId(userId);
	}
	
	public PageInfo<BaseUserEntity> getUserByRoleIdPagination(Integer offset, Integer limit, String roleId){
		
		PageHelper.offsetPage(offset, limit);
		List<BaseUserEntity> list =  baseUserRoleMappingMapper.selectUserByRoleIdPagination(roleId);
		PageInfo<BaseUserEntity> pi = new PageInfo<BaseUserEntity>(list);
		return pi;
	}
	
	public PageInfo<BaseUserEntity> getUnUserByRoleIdPagination(Integer offset, Integer limit, String roleId,String searchText){
		
		PageHelper.offsetPage(offset, limit);
		BaseUserRoleMappingEntity baseUserRoleMappingEntity = new BaseUserRoleMappingEntity();
		if(searchText!=null&&!"".equals(searchText)) {
			baseUserRoleMappingEntity.setName(searchText);
			baseUserRoleMappingEntity.setUsername(searchText);
		}
		baseUserRoleMappingEntity.setRoleId(roleId);
		List<BaseUserEntity> list =  baseUserRoleMappingMapper.selectUnUserByRoleIdPagination(baseUserRoleMappingEntity);
		PageInfo<BaseUserEntity> pi = new PageInfo<BaseUserEntity>(list);
		return pi;
	}
	
	public boolean deleteByUserIdAndRoleId(String roleId,String[] userIds) {
		
		int i = 0;
		BaseUserRoleMappingEntity baseUserRoleMappingEntity = new BaseUserRoleMappingEntity();
		baseUserRoleMappingEntity.setRoleId(roleId);
		for(int j=0; j<userIds.length; j++) {
			baseUserRoleMappingEntity.setUserId(userIds[j]);
			i  = baseUserRoleMappingMapper.deleteByUserIdAndRoleId(baseUserRoleMappingEntity);
		}
		return i > 0 ? true : false;
	}
	
	
	public PageInfo<BaseUserEntity> searchUserByRoleIdPagination(Integer offset, Integer limit, String roleId,String searchText){
		
		PageHelper.offsetPage(offset, limit);
		BaseUserRoleMappingEntity baseUserRoleMappingEntity = new BaseUserRoleMappingEntity();
		if(searchText!=null&&!"".equals(searchText)) {
			baseUserRoleMappingEntity.setName(searchText);
			baseUserRoleMappingEntity.setUsername(searchText);
		}
		baseUserRoleMappingEntity.setRoleId(roleId);
		List<BaseUserEntity> list =  baseUserRoleMappingMapper.searchUserByRoleIdPagination(baseUserRoleMappingEntity);
		PageInfo<BaseUserEntity> pi = new PageInfo<BaseUserEntity>(list);
		return pi;
	}
	
}