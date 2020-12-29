package com.dzb.console.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @Description 发送邮件服务类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:35:34
 */

@Service("MailService")
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleMail() {
		
		MimeMessage mimeMailMessage = null;
		
        try {
        	
        	mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            //邮件发送人
            mimeMessageHelper.setFrom("dzb@123.com");
            //邮件接收人
            mimeMessageHelper.setTo("cyy@123.com");
            //邮件主题
            mimeMessageHelper.setSubject("测试主题");
            //邮件内容
            mimeMessageHelper.setText("测试内容");
            
            FileSystemResource file = new FileSystemResource(new File("D:\\Documents\\项目文档\\表.xls"));
            mimeMessageHelper.addAttachment("表.xls", file);
            
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
