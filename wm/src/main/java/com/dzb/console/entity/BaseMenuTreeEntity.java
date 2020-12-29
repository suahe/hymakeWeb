package com.dzb.console.entity;

import java.util.List;

/**
 * @Description 菜单树实体
 * @author 38840472@qq.com
 * @date 2020-04-24 08:34:34
 */
public class BaseMenuTreeEntity {

	/** 菜单编号*/
	private String id;
	/** 菜单名称*/
	private String text;
	/** 菜单父编号*/
	private String pid;
	/** 资源地址**/
	private String url;
	/** 叶子节点**/
	private int isLeaf;
	/** 树的子节点信息**/
	private List<BaseMenuTreeEntity> nodes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<BaseMenuTreeEntity> getNodes() {
		return nodes;
	}
	public void setNodes(List<BaseMenuTreeEntity> nodes) {
		this.nodes = nodes;
	}
	
}
