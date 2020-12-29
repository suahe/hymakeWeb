package com.dzb.console.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseMenuEntity;
import com.dzb.console.mapper.BaseMenuMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
/**
 * @Description t_base_menu 表Service类
 * @author 38840472@qq.com
 * @date 2018-12-19 16:00:36
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseMenuService")
@Transactional
public class BaseMenuService {
	
	@Autowired
	private BaseMenuMapper baseMenuMapper;
	@Autowired
	private BaseMenuRoleMappingService baseMenuRoleMappingService;
	
	/**
	 * @Description 通过主键查询信息
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	public BaseMenuEntity getByPkey(String menuId){
	
		return baseMenuMapper.selectByPkey(menuId);
	}
	
	/**
	 * @Description  新增信息
	 * @param menuPid
	 * @param menuName
	 * @param url
	 * @param iconCss
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-23 09:53:42
	 */
	public int insert(String menuPid,String menuName,String url,String iconCss) {
		
		BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
		iconCss = (null == iconCss || "".equals(iconCss)) ? "fa-th-large" : iconCss;
		
		baseMenuEntity.setMenuId(IDUtil.getId());
		baseMenuEntity.setMenuName(menuName);
		baseMenuEntity.setMenuPid(menuPid);
		baseMenuEntity.setUrl(url);
		baseMenuEntity.setIconCss(iconCss);
		baseMenuEntity.setSequ(getMaxSequ()+1);
		baseMenuEntity.setIsDel(0);
		baseMenuEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseMenuEntity.setCreateTime(new Date());
		baseMenuEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseMenuEntity.setUpdateTime(new Date());
		return baseMenuMapper.insert(baseMenuEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseMenuEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	public int update(String menuId,String menuName,String url, String iconCss){
	
		BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
		baseMenuEntity.setMenuId(menuId);
		baseMenuEntity.setMenuName(menuName);
		baseMenuEntity.setUrl(url);
		baseMenuEntity.setIconCss(iconCss);
		baseMenuEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseMenuEntity.setUpdateTime(new Date());
		return baseMenuMapper.update(baseMenuEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	@Transactional
	public boolean delete(String menuId){
	
		int i = baseMenuMapper.delete(menuId);
		baseMenuRoleMappingService.deleteByMenuId(menuId);
		return i > 0 ? true : false;
	}
	
	/**
	 * @Description 获取菜单列表
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	public List<BaseMenuEntity> getMenuList(){
		
		return baseMenuMapper.selectMenuList();
	}
	
	public List<BaseMenuEntity> getAllMenuList(){
		
		return baseMenuMapper.selectAllMenuList();
	}
	
	public List<BaseMenuEntity> getMenuByRoleIds(List<String> roleIds){
		
		List<BaseMenuEntity> baseMenuList = new ArrayList<BaseMenuEntity>();
		for(String roleId:roleIds){
			List<BaseMenuEntity> list = baseMenuRoleMappingService.getMenuByRoleId(roleId);
			for(BaseMenuEntity baseMenuEntity:list) {
				// 判断menuId是否存在，确保在同一个用户有多个角色对应的资源存在交集时，获取并集资源
				if(!baseMenuList.contains(baseMenuEntity)) {
					baseMenuList.add(baseMenuEntity);
				}
			}
		}
		return baseMenuList;
	}
	
	public List<BaseMenuEntity> getMenuByActiveUser(){
		
		List<String> roleIds = UserInfoUtil.getRoleIds();
		return getMenuByRoleIds(roleIds);
	}
	
	public List<Tree<String>> selectMenuTreeByActiveUser() {
		
		List<BaseMenuEntity> baseMenuEntityList = getMenuByActiveUser();
		
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setChildrenKey("nodes");
		treeNodeConfig.setNameKey("text");
		treeNodeConfig.setParentIdKey("pid");
		treeNodeConfig.setDeep(3);
		
		for(BaseMenuEntity oBaseMenuEntity:baseMenuEntityList) {
			
			String id = oBaseMenuEntity.getMenuId();
			String pid = oBaseMenuEntity.getMenuPid();
			String name = oBaseMenuEntity.getMenuName();
			Integer sequ = oBaseMenuEntity.getSequ();
			String url = oBaseMenuEntity.getUrl();
			String icon = oBaseMenuEntity.getIconCss();
			TreeNode<String> treeNode = new TreeNode<>(id, pid, name, sequ);
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("icon", icon);
			extra.put("url", url);
			treeNode.setExtra(extra);
			nodeList.add(treeNode);
		}
		
		List<Tree<String>> treeList = TreeUtil.build(nodeList, "-", treeNodeConfig,
				(treeNode, tree) -> {
		            tree.setId(treeNode.getId());
		            tree.setParentId(treeNode.getParentId());
		            tree.setName(treeNode.getName());
		            tree.putExtra("extra", treeNode.getExtra());
		        });
		return treeList;
	}
	
	/**
	 * @Description 获取整棵信息，用于后台权限配置
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 14:57:59
	 */
	public List<Tree<String>> getMenuTree() {
		
		List<BaseMenuEntity> baseMenuEntityList = getAllMenuList();
		
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setChildrenKey("nodes");
		treeNodeConfig.setNameKey("text");
		treeNodeConfig.setParentIdKey("pid");
		treeNodeConfig.setDeep(3);
		
		for(BaseMenuEntity oBaseMenuEntity:baseMenuEntityList) {
			
			String id = oBaseMenuEntity.getMenuId();
			String pid = oBaseMenuEntity.getMenuPid();
			String name = oBaseMenuEntity.getMenuName();
			Integer sequ = oBaseMenuEntity.getSequ();
			TreeNode<String> treeNode = new TreeNode<>(id, pid, name, sequ);
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("checked", false);
			treeNode.setExtra(extra);
			nodeList.add(treeNode);
		}
		
		List<Tree<String>> treeList = TreeUtil.build(nodeList, "-", treeNodeConfig,
				(treeNode, tree) -> {
		            tree.setId(treeNode.getId());
		            tree.setParentId(treeNode.getParentId());
		            tree.setName(treeNode.getName());
		            tree.setWeight(treeNode.getWeight());
		            tree.putExtra("state", treeNode.getExtra());
		        });
		return treeList;
	}
	
	public int getMaxSequ() {
		
		Integer max = baseMenuMapper.selectMaxSequ();
		return (null == max)?0:max;
	}
	
	public int countByMenuPid(String menuPid){
		
		return baseMenuMapper.countByMenuPid(menuPid);
	}
	
	public BaseMenuEntity getDownBySequAndMenuPid(String menuPid, int sequ) {
		
		BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
		baseMenuEntity.setMenuPid(menuPid);
		baseMenuEntity.setSequ(sequ);
		return baseMenuMapper.selectDownBySequAndMenuPid(baseMenuEntity);
	}
	
	/**
	 * @Description 排序下移一位
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int down(String menuId) {
		
		int i = 0;
		BaseMenuEntity baseMenuEntity = getByPkey(menuId);
		if(null != baseMenuEntity) {
			int sequ = baseMenuEntity.getSequ();
			String menuPid = baseMenuEntity.getMenuPid();
			BaseMenuEntity downBaseMenuEntity = getDownBySequAndMenuPid(menuPid, sequ);
			if(null != downBaseMenuEntity) {
				String downMenuId = downBaseMenuEntity.getMenuId();
				int downSequ = downBaseMenuEntity.getSequ();
				updateSequ(downMenuId, sequ);
				i = updateSequ(menuId, downSequ);
			}
		}
		return i;
	}
	
	public BaseMenuEntity getUpBySequAndMenuPid(String menuPid, int sequ) {
		
		BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
		baseMenuEntity.setMenuPid(menuPid);
		baseMenuEntity.setSequ(sequ);
		return baseMenuMapper.selectUpBySequAndMenuPid(baseMenuEntity);
	}
	
	/**
	 * @Description 排序上移一位
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:08:43
	 */
	public int up(String menuId) {
		
		int i = 0;
		BaseMenuEntity baseMenuEntity = getByPkey(menuId);
		if(null != baseMenuEntity) {
			int sequ = baseMenuEntity.getSequ();
			String menuPid = baseMenuEntity.getMenuPid();
			BaseMenuEntity upBaseMenuEntity = getUpBySequAndMenuPid(menuPid, sequ);
			if(null != upBaseMenuEntity) {
				String upMenuId = upBaseMenuEntity.getMenuId();
				int upSequ = upBaseMenuEntity.getSequ();
				updateSequ(upMenuId, sequ);
				i = updateSequ(menuId, upSequ);
			}
		}
		return i;
	}
	
	/**
	 * @Description 更新排序序号
	 * @param menuId
	 * @param sequ
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:24:12
	 */
	public int updateSequ(String menuId, int sequ) {
		BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
		baseMenuEntity.setMenuId(menuId);
		baseMenuEntity.setSequ(sequ);
		baseMenuEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseMenuEntity.setUpdateTime(new Date());
 		return baseMenuMapper.updateSequ(baseMenuEntity);
	}
	
	
}