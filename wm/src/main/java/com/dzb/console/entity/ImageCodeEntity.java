package com.dzb.console.entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description 验证码实体类
 * @author 38840472@qq.com
 * @date 2020-04-16 08:47:45
 */
public class ImageCodeEntity {
	
	/** 验证码 */
    private String code;
    /** 判断过期时间 */
    private LocalDateTime expireTime;
    /** 生成的图片验证码 */
    private BufferedImage image;
 
    /** 验证码 */
    public String getCode() {
		return code;
	}

    /** 验证码 */
	public void setCode(String code) {
		this.code = code;
	}

	/** 判断过期时间 */
	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	/** 判断过期时间 */
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	/** 生成的图片验证码 */
	public BufferedImage getImage() {
		return image;
	}

	/** 生成的图片验证码 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public ImageCodeEntity(String code, int expireIn, BufferedImage image) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        this.image = image;
    }
 
    /**
     * @Description 判断验证码是否过期
     * @return
     * @author 38840472@qq.com
     * @date 2020-04-24 09:05:12
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
