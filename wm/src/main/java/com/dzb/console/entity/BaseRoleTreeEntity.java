package com.dzb.console.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色树实体
 * @author 38840472@qq.com
 * @date 2020-04-24 08:35:18
 */

public class BaseRoleTreeEntity {
	
	private static Map<String, Object> stateMap = new HashMap<String, Object>();
	
	static {
        stateMap.put("checked", true);
    }

	/** 角色编号*/
	private String id;
	/** 菜单名称*/
	private String text;
	/** 角色父编号*/
	private String pid;
	/** 叶子节点 */
	private int isLeaf;
	/** 树的子节点信息 */
	private List<BaseRoleTreeEntity> nodes;
	/** 状态 */
	private Map<String, Object> state;
	
	/** 角色编号*/
	public String getId() {
		return id;
	}
	
	/** 角色编号*/
	public void setId(String id) {
		this.id = id;
	}
	
	/** 菜单名称*/
	public String getText() {
		return text;
	}
	
	/** 菜单名称*/
	public void setText(String text) {
		this.text = text;
	}
	
	/** 角色父编号*/
	public String getPid() {
		return pid;
	}
	
	/** 角色父编号*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	/** 叶子节点 */
	public int getIsLeaf() {
		return isLeaf;
	}
	
	/** 叶子节点 */
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	/** 树的子节点信息 */
	public List<BaseRoleTreeEntity> getNodes() {
		return nodes;
	}
	/** 树的子节点信息 */
	public void setNodes(List<BaseRoleTreeEntity> nodes) {
		this.nodes = nodes;
	}
	
	/** 状态 */
	public Map<String, Object> getState() {
		return state;
	}
	
	/** 状态 */
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	
	/** 状态 */
	public void setState() {
        this.state = stateMap;
    }
	
}
