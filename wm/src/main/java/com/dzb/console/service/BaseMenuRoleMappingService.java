package com.dzb.console.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseMenuEntity;
import com.dzb.console.entity.BaseMenuRoleMappingEntity;
import com.dzb.console.entity.BaseRoleEntity;
import com.dzb.console.entity.BaseRoleTreeEntity;
import com.dzb.console.mapper.BaseMenuRoleMappingMapper;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Description t_base_menu_role_mapping 表Service类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:03
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseMenuRoleMappingService")
@Transactional
public class BaseMenuRoleMappingService {
	
	@Autowired
	private BaseMenuRoleMappingMapper baseMenuRoleMappingMapper;
	@Autowired
	private BaseRoleService baseRoleService;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	public PageInfo<BaseMenuRoleMappingEntity> selectPagination(Integer page, Integer rows, BaseMenuRoleMappingEntity baseMenuRoleMappingEntity) {
		
		PageHelper.startPage(page, rows);
		//PageHelper.orderBy("UPDATE_TIME DESC");
		List<BaseMenuRoleMappingEntity> list = baseMenuRoleMappingMapper.selectPagination(baseMenuRoleMappingEntity);
		PageInfo<BaseMenuRoleMappingEntity> pi = new PageInfo<BaseMenuRoleMappingEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param menuRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	public BaseMenuRoleMappingEntity getByPkey(String menuRoleMappingId){
	
		return baseMenuRoleMappingMapper.selectByPkey(menuRoleMappingId);
	}
	
	/**
	 * @Description 新增信息
	 * @param menuId
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-25 11:11:54
	 */
	public boolean insert(String menuId,String roleId) {
		
		BaseMenuRoleMappingEntity baseMenuRoleMappingEntity = new BaseMenuRoleMappingEntity();
		
		baseMenuRoleMappingEntity.setMenuRoleMappingId(IDUtil.getId());
		baseMenuRoleMappingEntity.setMenuId(menuId);
		baseMenuRoleMappingEntity.setRoleId(roleId);
		int i = baseMenuRoleMappingMapper.insert(baseMenuRoleMappingEntity);
		return i > 0 ? true:false;
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	public int update(BaseMenuRoleMappingEntity baseMenuRoleMappingEntity){
	
		return baseMenuRoleMappingMapper.update(baseMenuRoleMappingEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param menuRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	public boolean delete(String menuId,String roleId){
	
		BaseMenuRoleMappingEntity baseMenuRoleMappingEntity = new BaseMenuRoleMappingEntity();
		
		baseMenuRoleMappingEntity.setMenuRoleMappingId(IDUtil.getId());
		baseMenuRoleMappingEntity.setMenuId(menuId);
		baseMenuRoleMappingEntity.setRoleId(roleId);
		int i = baseMenuRoleMappingMapper.delete(baseMenuRoleMappingEntity);
		return i > 0 ? true:false;
	}
	
	/**
	 * @Description 通过菜单编号获取角色编号
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	public List<String> getRoleIdByMenuId(String menuId){
		
		return baseMenuRoleMappingMapper.selectRoleIdByMenuId(menuId);
	}
	
	public List<BaseRoleEntity> getRoleIdsByMenuId(String menuId){
		
		return baseMenuRoleMappingMapper.selectRoleIdsByMenuId(menuId);
	}
	
	public List<BaseRoleTreeEntity> selectTree(String menuId) {
		
		List<BaseRoleTreeEntity> baseRoleTreeList = new ArrayList<BaseRoleTreeEntity>();
		List<BaseRoleEntity> baseRoleList = baseRoleService.getAllRoleist();
		List<String> roleIds = getRoleIdByMenuId(menuId);
		
		//查找一级菜单 rolePid = ""
		for(BaseRoleEntity baseRoleEntity:baseRoleList) {
			if(null == baseRoleEntity.getRolePid() || "".equals(baseRoleEntity.getRolePid())) {
				
				BaseRoleTreeEntity baseRoleTreeEntity = new BaseRoleTreeEntity();
				baseRoleTreeEntity.setId(baseRoleEntity.getRoleId());
				baseRoleTreeEntity.setPid(baseRoleEntity.getRolePid());
				baseRoleTreeEntity.setIsLeaf(baseRoleEntity.getIsLeaf());
				baseRoleTreeEntity.setText(baseRoleEntity.getRoleName());
				for(String roleId:roleIds) {
					if(roleId.equals(baseRoleEntity.getRoleId())) {
						baseRoleTreeEntity.setState();
					}
				}
				baseRoleTreeList.add(baseRoleTreeEntity);
			}
		}
		for(BaseRoleTreeEntity baseRoleTreeEntity:baseRoleTreeList) {
			baseRoleTreeEntity.setNodes(assembleNodes(baseRoleTreeEntity.getId(),baseRoleList,roleIds));
		}
		return baseRoleTreeList;
	}
	
	private List<BaseRoleTreeEntity> assembleNodes(String roleId,List<BaseRoleEntity> baseRoleList,List<String> roleIds){
		
		List<BaseRoleTreeEntity> baseRoleTreeEntityChildList = new ArrayList<BaseRoleTreeEntity>();
		//遍历列表，比较传递过来的父编号（roleId）和列表中的父编号（rolePid）比较
		for(BaseRoleEntity baseRoleEntity:baseRoleList) {
			if(null != baseRoleEntity.getRolePid()) {
				if(baseRoleEntity.getRolePid().equals(roleId)) {
					BaseRoleTreeEntity baseRoleTreeEntity = new BaseRoleTreeEntity();
					baseRoleTreeEntity.setId(baseRoleEntity.getRoleId());
					baseRoleTreeEntity.setPid(baseRoleEntity.getRolePid());
					baseRoleTreeEntity.setText(baseRoleEntity.getRoleName());
					baseRoleTreeEntity.setIsLeaf(baseRoleEntity.getIsLeaf());
					for(String id:roleIds) {
						if(id.equals(baseRoleEntity.getRoleId())) {
							baseRoleTreeEntity.setState();
						}
					}
					baseRoleTreeEntityChildList.add(baseRoleTreeEntity);
				}
			}
		}
		for(BaseRoleTreeEntity baseRoleTreeEntity:baseRoleTreeEntityChildList) {
			if(0 == baseRoleTreeEntity.getIsLeaf()) {
				baseRoleTreeEntity.setNodes(assembleNodes(baseRoleTreeEntity.getId(),baseRoleList,roleIds));
			}
		}
		if(baseRoleTreeEntityChildList.size() == 0) {
			return null;
		}
		return baseRoleTreeEntityChildList;
	}
	
	
	/**
	 * @Description 通过menuId删除映射的角色记录（用于删除菜单时级联同步删除映射表记录）
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-15 17:24:59
	 */
	public int deleteByMenuId(String menuId) {
		
		return baseMenuRoleMappingMapper.deleteByMenuId(menuId);
	}
	

	/**
	 * @Description 通过角色获取菜单信息
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 15:03:44
	 */
	public List<BaseMenuEntity> getMenuByRoleId(String roleId){
		
		return baseMenuRoleMappingMapper.selectMenuByRoleId(roleId);
	}

}