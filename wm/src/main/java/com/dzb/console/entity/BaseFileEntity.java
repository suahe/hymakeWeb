package com.dzb.console.entity;

/**
 * @Description 文件内容实体类
 * @author 38840472@qq.com
 * @date 2020-05-11 11:14:09
 */
public class BaseFileEntity {

	/** 文档编号 */
	private String documentId;
	/** 文件名 */
	private String name;
	/** 扩展名 */
	private String ext;
	/** 文件字节 */
	private byte[] bytes;
	
	/** 文档编号 */
	public String getDocumentId() {
		return documentId;
	}
	
	/** 文档编号 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	/** 文件名 */
	public String getName() {
		return name;
	}
	
	/** 文件名 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 扩展名 */
	public String getExt() {
		return ext;
	}
	
	/** 扩展名 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	/** 文件字节 */
	public byte[] getBytes() {
		return bytes;
	}
	
	/** 文件字节 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public BaseFileEntity(){
		
	}
	
	/**
	 * @param documentId 文档编号
	 * @param name 文件名
	 * @param ext 扩展名
	 * @param bytes 文件字节
	 */
	public BaseFileEntity(String documentId, String name, String ext, byte[] bytes) {
		
		this.documentId = documentId;
		this.name = name;
		this.ext = ext;
		this.bytes = bytes;
	}
	
}
