package com.dzb.console.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseRoleEntity;
import com.dzb.console.entity.BaseRoleTreeEntity;
import com.dzb.console.mapper.BaseRoleMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
/**
 * @Description t_base_role 表Service类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:15
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseRoleService")
@Transactional
public class BaseRoleService {
	
	@Autowired
	private BaseRoleMapper baseRoleMapper;
	
	/**
	 * @Description 通过主键查询信息
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	public BaseRoleEntity getByPkey(String roleId){
	
		return baseRoleMapper.selectByPkey(roleId);
	}
	
	/**
	 * @Description 新增信息
	 * @param rolePid
	 * @param roleName
	 * @param isLeaf
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-24 11:29:46
	 */
	public boolean insert(String rolePid,String roleName,int isLeaf){
		
		BaseRoleEntity baseRoleEntity = new BaseRoleEntity();
		if(1 == isLeaf) {
			updateIsLeaf(rolePid,0);
		}
		baseRoleEntity.setRoleId(IDUtil.getId());
		baseRoleEntity.setRoleName(roleName);
		baseRoleEntity.setRolePid(rolePid);
		baseRoleEntity.setIsLeaf(isLeaf);
		baseRoleEntity.setIsDel(0);
		baseRoleEntity.setSequ(getMaxSequ()+1);
		baseRoleEntity.setCreateTime(new Date());
		baseRoleEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseRoleEntity.setUpdateTime(new Date());
		baseRoleEntity.setUpdateUserid(UserInfoUtil.getUserId());
		int i = baseRoleMapper.insert(baseRoleEntity);
		return i == 1?true:false;
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseRoleEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	public boolean update(String roleId,String roleName){
	
		BaseRoleEntity baseRoleEntity = new BaseRoleEntity();
		baseRoleEntity.setRoleId(roleId);
		baseRoleEntity.setRoleName(roleName);
		baseRoleEntity.setUpdateTime(new Date());
		baseRoleEntity.setUpdateUserid(UserInfoUtil.getUserId());
		int i = baseRoleMapper.update(baseRoleEntity);
		return i == 1?true:false;
	}
	
	/**
	 * @Description 删除信息
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	public boolean delete(String roleId){
	
		isLeaf(roleId);
		int i = baseRoleMapper.delete(roleId);
		return i > 0?true:false;
	}
	
	public List<BaseRoleEntity> getAllRoleist(){
		
		return baseRoleMapper.selectAllRoleList();
	}
	
	public List<BaseRoleTreeEntity> selectTree() {
		
		List<BaseRoleTreeEntity> baseRoleTreeList = new ArrayList<BaseRoleTreeEntity>();
		List<BaseRoleEntity> baseRoleList = getAllRoleist();	//查询菜单列表
		//查找一级菜单 rolePid = ""
		for(BaseRoleEntity baseRoleEntity:baseRoleList) {
			if(null == baseRoleEntity.getRolePid() || "".equals(baseRoleEntity.getRolePid())) {
				
				BaseRoleTreeEntity baseRoleTreeEntity = new BaseRoleTreeEntity();
				baseRoleTreeEntity.setId(baseRoleEntity.getRoleId());
				baseRoleTreeEntity.setPid(baseRoleEntity.getRolePid());
				baseRoleTreeEntity.setIsLeaf(baseRoleEntity.getIsLeaf());
				baseRoleTreeEntity.setText(baseRoleEntity.getRoleName());
				baseRoleTreeList.add(baseRoleTreeEntity);
			}
		}
		for(BaseRoleTreeEntity baseRoleTreeEntity:baseRoleTreeList) {
			baseRoleTreeEntity.setNodes(assembleNodes(baseRoleTreeEntity.getId(),baseRoleList));
		}
		return baseRoleTreeList;
	}
	
	private List<BaseRoleTreeEntity> assembleNodes(String roleId,List<BaseRoleEntity> baseRoleList){
		
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
					baseRoleTreeEntityChildList.add(baseRoleTreeEntity);
				}
			}
		}
		for(BaseRoleTreeEntity baseRoleTreeEntity:baseRoleTreeEntityChildList) {
			if(0 == baseRoleTreeEntity.getIsLeaf()) {
				baseRoleTreeEntity.setNodes(assembleNodes(baseRoleTreeEntity.getId(),baseRoleList));
			}
		}
		if(baseRoleTreeEntityChildList.size() == 0) {
			return null;
		}
		return baseRoleTreeEntityChildList;
	}
	
	public int updateIsLeaf(String roleId,int isLeaf) {
		
		BaseRoleEntity baseRoleEntity = new BaseRoleEntity();
		baseRoleEntity.setRoleId(roleId);
		baseRoleEntity.setIsLeaf(isLeaf);
		baseRoleEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseRoleEntity.setUpdateTime(new Date());
		return baseRoleMapper.updateIsLeaf(baseRoleEntity);
	}
	
	public int getMaxSequ() {
	
		Integer max = baseRoleMapper.selectMaxSequ();
		return (null == max)?0:max;
	}
	
	public int isLeaf(String roleId) {
		
		int t = 0;
		BaseRoleEntity baseRoleEntity = getByPkey(roleId);
		if(null != baseRoleEntity) {
			String rolePid = baseRoleEntity.getRolePid();
			if(1 == countByRolePid(rolePid)) {
				t = updateIsLeaf(rolePid,1);
			}
		}
		return t;
	}
	
	public int countByRolePid(String menuPid){
		
		return baseRoleMapper.countByRolePid(menuPid);
	}
	
}