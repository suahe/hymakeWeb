package com.dzb.console.entity;

import org.springframework.http.MediaType;

public class MediaFileEntity {

	/** 媒体文件类型 */
	private MediaType mediaType;
	/** 媒体文件内容 */
	private byte[] media;
	
	/** 媒体文件类型 */
	public MediaType getMediaType() {
		return mediaType;
	}
	
	/** 媒体文件类型 */
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	/** 媒体文件内容 */
	public byte[] getMedia() {
		return media;
	}
	
	/** 媒体文件内容 */
	public void setMedia(byte[] media) {
		this.media = media;
	}
	
	/**
	 * @param mediaType 媒体文件类型
	 * @param media 媒体文件内容
	 */
	public MediaFileEntity(MediaType mediaType, byte[] media) {
		this.mediaType = mediaType;
		this.media = media;
	}
}
