package com.dzb.console.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseDepartmentEntity;
import com.dzb.console.entity.BaseDepartmentUserMappingEntity;
import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.mapper.BaseDepartmentMapper;
import com.dzb.console.mapper.BaseDepartmentUserMappingMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

/**
 * @Description t_base_department 表Service类
 * @author 38840472@qq.com
 * @date 2020-05-26 23:22:37
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseDepartmentService")
@Transactional
public class BaseDepartmentService {

	private static final Logger log = LoggerFactory.getLogger(BaseDepartmentService.class);
	
	@Autowired
	private BaseDepartmentMapper baseDepartmentMapper;
	@Autowired
	private BaseDepartmentUserMappingMapper baseDepartmentUserMappingMapper;
	
	/**
	 * @Description 通过主键查询信息
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	public BaseDepartmentEntity getByPkey(String departmentId){
	
		log.debug("通过主键查询信息");
		return baseDepartmentMapper.selectByPkey(departmentId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseDepartmentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	public int insert(BaseDepartmentEntity baseDepartmentEntity) {

		log.debug("新增信息");
		baseDepartmentEntity.setDepartmentId(IDUtil.getId());
		baseDepartmentEntity.setIsDel(0);
		baseDepartmentEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseDepartmentEntity.setCreateTime(new Date());
		baseDepartmentEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseDepartmentEntity.setUpdateTime(new Date());
		return baseDepartmentMapper.insert(baseDepartmentEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseDepartmentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	public int update(BaseDepartmentEntity baseDepartmentEntity){
	
		log.debug("编辑信息");
		baseDepartmentEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseDepartmentEntity.setUpdateTime(new Date());
		return baseDepartmentMapper.update(baseDepartmentEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	@Transactional
	public int delete(String departmentId){
	
		log.debug("删除信息");
		/* 先删除部门人员映射表 */
		deleteByDepartmentId(departmentId);
		return baseDepartmentMapper.delete(departmentId);
	}
	
	/**
	 * @Description 查询所有的部门信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:37:36
	 */
	public List<BaseDepartmentEntity> getAllDepartmentList(){
		
		return baseDepartmentMapper.selectAllDepartment();
	}
	
	/**
	 * @Description 部门信息树
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:37:50
	 */
	public List<Tree<String>> getDepartmentTree() {
		
		List<BaseDepartmentEntity> baseDepartmentEntityList = getAllDepartmentList();
		
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setChildrenKey("nodes");
		treeNodeConfig.setNameKey("text");
		treeNodeConfig.setParentIdKey("pid");
		
		for(BaseDepartmentEntity oBaseDepartmentEntity: baseDepartmentEntityList) {
			
			String id = oBaseDepartmentEntity.getDepartmentId();
			String pid = oBaseDepartmentEntity.getDepartmentPid();
			String name = oBaseDepartmentEntity.getDepartmentName();
			Integer sequ = oBaseDepartmentEntity.getSequ();
			TreeNode<String> treeNode = new TreeNode<>(id, pid, name, sequ);
			nodeList.add(treeNode);
		}
		
		List<Tree<String>> treeList = TreeUtil.build(nodeList, "-", treeNodeConfig,
				(treeNode, tree) -> {
		            tree.setId(treeNode.getId());
		            tree.setParentId(treeNode.getParentId());
		            tree.setName(treeNode.getName());
		            tree.setWeight(treeNode.getWeight());
		        });
		return treeList;
	}
	
	/**
	 * @Description 查询未被部门选中的人员列表
	 * @param offset
	 * @param limit
	 * @param departmentId
	 * @param searchText
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 08:58:15
	 */
	public PageInfo<BaseUserEntity> getUnUserByDepartmentIdPagination(Integer offset, Integer limit, String departmentId, String searchText){
		
		PageHelper.offsetPage(offset, limit);
		BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity = new BaseDepartmentUserMappingEntity();
		if(null != searchText && !"".equals(searchText)) {
			baseDepartmentUserMappingEntity.setName(searchText);
			baseDepartmentUserMappingEntity.setUsername(searchText);
		}
		baseDepartmentUserMappingEntity.setDepartmentId(departmentId);

		return new PageInfo<BaseUserEntity>(baseDepartmentUserMappingMapper.selectUnUserByDepartmentIdPagination(baseDepartmentUserMappingEntity));
	}
	
	/**
	 * @Description 查询部门对应的人员
	 * @param offset
	 * @param limit
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 10:42:00
	 */
	public PageInfo<BaseUserEntity> getUserByDepartmentIdPagination(Integer offset, Integer limit, String departmentId){
		
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<BaseUserEntity>(baseDepartmentUserMappingMapper.selectUserByDepartmentIdPagination(departmentId));
	}
	
	/**
	 * @Description 增加用户部门映射关系
	 * @param departmentId
	 * @param userIds
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 09:53:07
	 */
	public int insertDepartmentIdAndUserIds(String departmentId, String[] userIds) {
		
		int i = 0;
		BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity = new BaseDepartmentUserMappingEntity();
		baseDepartmentUserMappingEntity.setDepartmentId(departmentId);
		baseDepartmentUserMappingEntity.setIsDel(0);
		baseDepartmentUserMappingEntity.setIsLeader(0);
		baseDepartmentUserMappingEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseDepartmentUserMappingEntity.setCreateTime(new Date());
		baseDepartmentUserMappingEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseDepartmentUserMappingEntity.setUpdateTime(new Date());
		for(int j=0; j<userIds.length; j++) {
			baseDepartmentUserMappingEntity.setDepartmentUserId(IDUtil.getId());
			baseDepartmentUserMappingEntity.setUserId(userIds[j]);
			i  = baseDepartmentUserMappingMapper.insert(baseDepartmentUserMappingEntity);
			i = i + 1;
		}
		return i;
	}
	
	/**
	 * @Description 移除部门人员映射关系
	 * @param departmentId
	 * @param userIds
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 10:41:26
	 */
	public int deleteByUserIdAndDepartmentId(String departmentId, String[] userIds) {
		
		int i = 0;
		BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity = new BaseDepartmentUserMappingEntity();
		baseDepartmentUserMappingEntity.setDepartmentId(departmentId);
		for(int j=0; j<userIds.length; j++) {
			baseDepartmentUserMappingEntity.setUserId(userIds[j]);
			i  = baseDepartmentUserMappingMapper.deleteByUserIdAndDepartmentId(baseDepartmentUserMappingEntity);
			i = i + 1;
		}
		return i;
	}
	
	/**
	 * @Description 通过用户编号查找用户部门名称
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	public BaseDepartmentEntity getDepartmentByUserId(String userId) {
		
		log.debug("通过用户编号查找用户部门名称");
		return baseDepartmentUserMappingMapper.selectDepartmentByUserId(userId);
	}
	
	public int deleteByDepartmentId(String departmentId) {
		
		return baseDepartmentUserMappingMapper.deleteByDepartmentId(departmentId);
	}
	
}