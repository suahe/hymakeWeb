package com.hymake.web.entity;

public class BaseTypeCacheEntity {

	/** 名称 */
	private String name;
	/** 值 */
	private String value;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	BaseTypeCacheEntity() {
		
	}
	
	/**
	 * @param name 名称
	 * @param value 值
	 */
	public BaseTypeCacheEntity(String name, String value) {
		
		this.name = name;
		this.value = value;
	}
	
}
