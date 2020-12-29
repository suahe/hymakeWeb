package com.dzb.console.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseTypeTreeEntity;
import com.dzb.console.mapper.BaseTypeTreeMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

/**
 * @Description t_base_type_tree 表Service类
 * @author 38840472@qq.com
 * @date 2020-05-14 10:41:46
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseTypeTreeService")
@Transactional
public class BaseTypeTreeService {

	private static final Logger log = LoggerFactory.getLogger(BaseTypeTreeService.class);
	
	@Autowired
	private BaseTypeTreeMapper baseTypeTreeMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	public List<Tree<String>> getTree(BaseTypeTreeEntity baseTypeTreeEntity) {
		
		log.debug("查询树型数据列表");

		List<BaseTypeTreeEntity> BaseTypeTreeEntityList = baseTypeTreeMapper.selectPagination(baseTypeTreeEntity);
		
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setChildrenKey("nodes");
		treeNodeConfig.setNameKey("text");
		treeNodeConfig.setParentIdKey("pid");
		
		for(BaseTypeTreeEntity oBaseTypeTreeEntity:BaseTypeTreeEntityList) {
			
			String id = oBaseTypeTreeEntity.getBaseTypeTreeId();
			String pid = oBaseTypeTreeEntity.getBaseTypeTreePid();
			String name = oBaseTypeTreeEntity.getName();
			nodeList.add(new TreeNode<>(id, pid, name, oBaseTypeTreeEntity.getValue()));
		}
		
		List<Tree<String>> treeList = TreeUtil.build(nodeList, "-", treeNodeConfig,
				(treeNode, tree) -> {
		            tree.setId(treeNode.getId());
		            tree.setParentId(treeNode.getParentId());
		            tree.setName(treeNode.getName() + " - " + treeNode.getWeight());
		        });
		return treeList;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	public BaseTypeTreeEntity getByPkey(String baseTypeTreeId){
	
		log.debug("通过主键查询信息");
		return baseTypeTreeMapper.selectByPkey(baseTypeTreeId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseTypeId
	 * @param baseTypeTreePid
	 * @param name
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 21:05:44
	 */
	public int insert(String baseTypeId, String baseTypeTreePid, String name, String value) {

		log.debug("新增信息");
		BaseTypeTreeEntity baseTypeTreeEntity = new BaseTypeTreeEntity();
		int max = getMaxByBaseTypeId(baseTypeId);
		String baseTypeTreeId = IDUtil.getId();
		baseTypeTreeEntity.setBaseTypeTreeId(baseTypeTreeId);
		baseTypeTreeEntity.setBaseTypeId(baseTypeId);
		baseTypeTreeEntity.setBaseTypeTreePid(baseTypeTreePid);
		baseTypeTreeEntity.setName(name);
		baseTypeTreeEntity.setValue(value);
		baseTypeTreeEntity.setSequ(max + 1);
		baseTypeTreeEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseTypeTreeEntity.setCreateTime(new Date());
		baseTypeTreeEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseTypeTreeEntity.setUpdateTime(new Date());
		int i = baseTypeTreeMapper.insert(baseTypeTreeEntity);
		updateNodeFullName(baseTypeTreeId);
		return i;
	}
	
	/**
	 * @Description 通过子节点的ID去遍历上一级父节点
	 * @param baseTypeTreeId
	 * @param list
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-09 10:34:26
	 */
	private List<String> parentName(String baseTypeTreeId,List<String> list){
		
		BaseTypeTreeEntity baseTypeTreeEntity = getByPkey(baseTypeTreeId);
		if(null == baseTypeTreeEntity) {
			return null;
		}else {
			list.add(baseTypeTreeEntity.getName());
			parentName(baseTypeTreeEntity.getBaseTypeTreePid(), list);
		}
		return list;
		
	}
	
	/**
	 * @Description 通过叶子节点获取全称信息
	 * @param stageId
	 * @param delimiter
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-09 10:44:27
	 */
	public String getTreeFullName(String baseTypeTreeId, CharSequence delimiter) {
		
		List<String> list = new ArrayList<String>();
		list = parentName(baseTypeTreeId, list);
		list = null == list ? new ArrayList<String>() : list;
		Collections.reverse(list);
		return String.join(delimiter, list);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	public int update(String baseTypeTreeId, String baseTypeTreePid, String name, String value){
	
		log.debug("编辑信息");
		BaseTypeTreeEntity baseTypeTreeEntity = new BaseTypeTreeEntity();
		baseTypeTreeEntity.setBaseTypeTreeId(baseTypeTreeId);
		baseTypeTreeEntity.setName(name);
		baseTypeTreeEntity.setValue(value);
		baseTypeTreeEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseTypeTreeEntity.setUpdateTime(new Date());
 		int i = baseTypeTreeMapper.update(baseTypeTreeEntity);
 		updateNodeFullName(baseTypeTreeId);
		return i;
	}
	
	/**
	 * @Description 更新叶子节点的全称
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-11 09:57:53
	 */
	public int updateNodeFullName(String baseTypeTreeId) {
		
		int i = 0;
		List<BaseTypeTreeEntity> list = getAllByBaseTypeTreeId(baseTypeTreeId);
		for(BaseTypeTreeEntity baseTypeTreeEntity: list) {
			String stageFullName = getTreeFullName(baseTypeTreeEntity.getBaseTypeTreeId(), "-");
			baseTypeTreeEntity.setFullName(stageFullName);
			i = baseTypeTreeMapper.updateFullName(baseTypeTreeEntity);
		}
		return i;
	}
	
	
	/**
	 * @Description 通过项目类型父编号获取所有的子集信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-08 08:58:40
	 */
	public List<BaseTypeTreeEntity> getAllByBaseTypeTreeId(String baseTypeTreeId){
		
		List<BaseTypeTreeEntity> list = new ArrayList<BaseTypeTreeEntity>();
		list = assembleNodes(baseTypeTreeId, list);
		list = (null == list) ? new ArrayList<BaseTypeTreeEntity>() : list;
		BaseTypeTreeEntity baseTypeTreeEntity = getByPkey(baseTypeTreeId);
		list.add(baseTypeTreeEntity);
		return list;
		
	}
	
	private List<BaseTypeTreeEntity> assembleNodes(String baseTypeTreeId,List<BaseTypeTreeEntity> list){
		
		List<BaseTypeTreeEntity> l = getByBaseTypeTreePid(baseTypeTreeId);
		if(l.size() > 0) {
			for(BaseTypeTreeEntity baseTypeTreeEntity: l) {
				BaseTypeTreeEntity oBaseTypeTreeEntity = new BaseTypeTreeEntity();
				BeanUtils.copyProperties(baseTypeTreeEntity,  oBaseTypeTreeEntity);
				list.add(oBaseTypeTreeEntity);
				assembleNodes(oBaseTypeTreeEntity.getBaseTypeTreeId(), list);
			}
		}else {
			return null;
		}
		return list;
	}
	
	public List<BaseTypeTreeEntity> getByBaseTypeTreePid(String baseTypeTreePid){
		
		return baseTypeTreeMapper.selectByBaseTypeTreePid(baseTypeTreePid);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	public int delete(String baseTypeTreeId){
	
		log.debug("删除信息");
		return baseTypeTreeMapper.delete(baseTypeTreeId);
	}
	
	/**
	 * @Description 通过数据字典编号查询排序序列的最大值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 19:37:27
	 */
	public int getMaxByBaseTypeId(String baseTypeId) {
		
		Integer max = baseTypeTreeMapper.selectMaxByBaseTypeId(baseTypeId);
		return (null == max) ? 0 : max;
	}
	
	public BaseTypeTreeEntity getDownBySequAndBaseTypeTreePid(String baseTypeTreePid, int sequ) {
		
		BaseTypeTreeEntity baseTypeTreeEntity = new BaseTypeTreeEntity();
		baseTypeTreeEntity.setBaseTypeTreePid(baseTypeTreePid);
		baseTypeTreeEntity.setSequ(sequ);
		return baseTypeTreeMapper.selectDownBySequAndBaseTypeTreePid(baseTypeTreeEntity);
	}
	
	/**
	 * @Description 排序下移一位
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int down(String baseTypeTreeId) {
		
		int i = 0;
		BaseTypeTreeEntity baseTypeTreeEntity = getByPkey(baseTypeTreeId);
		if(null != baseTypeTreeEntity) {
			int sequ = baseTypeTreeEntity.getSequ();
			String baseTypeTreePid = baseTypeTreeEntity.getBaseTypeTreePid();
			BaseTypeTreeEntity downBaseTypeTreeEntity = getDownBySequAndBaseTypeTreePid(baseTypeTreePid, sequ);
			if(null != downBaseTypeTreeEntity) {
				String downBaseTypeTreeId = downBaseTypeTreeEntity.getBaseTypeTreeId();
				int downSequ = downBaseTypeTreeEntity.getSequ();
				
				updateSequ(downBaseTypeTreeId, sequ);
				i = updateSequ(baseTypeTreeId, downSequ);
			}
		}
		return i;
	}
	
	public BaseTypeTreeEntity getUpBySequAndBaseTypeTreePid(String baseTypeTreePid, int sequ) {
		
		BaseTypeTreeEntity baseTypeTreeEntity = new BaseTypeTreeEntity();
		baseTypeTreeEntity.setBaseTypeTreePid(baseTypeTreePid);
		baseTypeTreeEntity.setSequ(sequ);
		return baseTypeTreeMapper.selectUpBySequAndBaseTypeTreePid(baseTypeTreeEntity);
	}
	
	/**
	 * @Description 排序上移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int up(String baseTypeTreeId) {
		
		int i = 0;
		BaseTypeTreeEntity baseTypeTreeEntity = getByPkey(baseTypeTreeId);
		if(null != baseTypeTreeEntity) {
			int sequ = baseTypeTreeEntity.getSequ();
			String baseTypeTreePid = baseTypeTreeEntity.getBaseTypeTreePid();
			BaseTypeTreeEntity upBaseTypeTreeEntity = getUpBySequAndBaseTypeTreePid(baseTypeTreePid, sequ);
			if(null != upBaseTypeTreeEntity) {
				String uptBaseTypeTreeId = upBaseTypeTreeEntity.getBaseTypeTreeId();
				int upSequ = upBaseTypeTreeEntity.getSequ();
				
				updateSequ(uptBaseTypeTreeId, sequ);
				i = updateSequ(baseTypeTreeId, upSequ);
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
	public int updateSequ(String baseTypeTreeId, int sequ) {
		BaseTypeTreeEntity baseTypeTreeEntity = new BaseTypeTreeEntity();
		baseTypeTreeEntity.setBaseTypeTreeId(baseTypeTreeId);
		baseTypeTreeEntity.setSequ(sequ);
		baseTypeTreeEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseTypeTreeEntity.setUpdateTime(new Date());
 		return baseTypeTreeMapper.updateSequ(baseTypeTreeEntity);
	}
	
}