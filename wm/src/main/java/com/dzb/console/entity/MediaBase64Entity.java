package com.dzb.console.entity;

import org.springframework.http.MediaType;

public class MediaBase64Entity {

	/** 媒体文件类型 */
	private MediaType mediaType;
	/** 扩展名 */
	private String ext;
	/** 媒体文件内容 */
	private byte[] media;
	/** 媒体文件内容 */
	private String body;
	
	/** 媒体文件类型 */
	public MediaType getMediaType() {
		return mediaType;
	}
	
	/** 媒体文件类型 */
	public void setMediaType(String mediaType) {
		this.mediaType = MediaType.valueOf(mediaType);
	}

	/** 扩展名 */
	public String getExt() {
		return ext;
	}
	
	/** 扩展名 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	/** 媒体文件内容 */
	public byte[] getMedia() {
		return media;
	}
	
	/** 媒体文件内容 */
	public void setMedia(byte[] media) {
		this.media = media;
	}
	
	/** 媒体文件内容 */
	public String getBody() {
		return body;
	}
	
	/** 媒体文件内容 */
	public void setBody(String body) {
		this.body = body;
	}
	
	MediaBase64Entity(){
		
	}
	
	public MediaBase64Entity(String mediaType, String ext, byte[] media, String body){
		
		this.mediaType = MediaType.valueOf(mediaType);
		this.ext = ext;
		this.media = media;
		this.body = body;
	}
	
}
