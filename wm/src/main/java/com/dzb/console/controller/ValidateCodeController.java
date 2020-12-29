package com.dzb.console.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.dzb.console.entity.ImageCodeEntity;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * @Description 生成验证码控制器类
 * @author 38840472@qq.com
 * @date 2020-04-16 10:54:17
 */

@Controller
@RequestMapping("vcc")
public class ValidateCodeController {
	 
    @Autowired
    private DefaultKaptcha defaultKaptcha;
 
    /** 处理session */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping(value = "/code/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String createText = defaultKaptcha.createText();
    	BufferedImage challenge = defaultKaptcha.createImage(createText);
    	ImageCodeEntity imageCodeEntity = new ImageCodeEntity(createText, 60, challenge);
        sessionStrategy.setAttribute(new ServletWebRequest(request),Constants.KAPTCHA_SESSION_KEY,imageCodeEntity);
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        ImageIO.write(challenge, "jpeg", byteArrayOutputStream);
        
        return byteArrayOutputStream.toByteArray();
    }
    
    
 
}