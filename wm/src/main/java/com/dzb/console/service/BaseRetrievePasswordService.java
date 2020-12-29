package com.dzb.console.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.dzb.console.entity.BaseRetrievePasswordEntity;
import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.mapper.BaseRetrievePasswordMapper;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * @Description t_base_retrieve_password 表Service类
 * @author 38840472@qq.com
 * @date 2019-01-16 11:04:43
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseRetrievePasswordService")
@Transactional
public class BaseRetrievePasswordService {
	
	@Autowired
	private BaseRetrievePasswordMapper baseRetrievePasswordMapper;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Configuration configuration;
	@Autowired
	private BaseUserService baseUserService;
	
	private final static int SEND_STATE_SUCCESS = 1;
	private final static int SEND_STATE_ERROR_MAIL = -1;
	private final static int SEND_STATE_FAIL = 0;
	@Value("${spring.mail.username}") 
	private String from;
	
	/**
	 * @Description 修改密码
	 * @param email
	 * @param securityCode
	 * @param lp
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 16:05:57
	 */
	public boolean resetPassword(String email,String securityCode,String lp) {
		
		BaseUserEntity baseUserEntity = baseUserService.selectByMail(email);
		int i = 0;
		boolean b = false;
		if(null != baseUserEntity) {
			String userId = baseUserEntity.getUserId();
			String retrievePasswordId = verifySecurityCodeByUserId(userId,securityCode);
			if(null != retrievePasswordId) {
				i = baseUserService.updatePassword(userId, lp);
				if (i > 0) {
					int t = updateSecurityCodeUseTimeByRetrievePasswordId(retrievePasswordId);
					if(t > 0) {
						b = true;
					}
				}
			}
		}
		return b;
	}
	
	/**
	 * @Description 验证验证码信息是否正确
	 * @param email 邮箱地址
	 * @param securityCode 验证码
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 15:23:41
	 */
	public boolean verifySecurityCode(String email,String securityCode) {
		
		return verifySecurityCodeByEmail(email,securityCode);
	}
	

	/**
	 * @Description 发送验证码邮件
	 * @param email
	 * @param remoteAddress
	 * @param area
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 14:49:42
	 */
	public int sendMail(String email,String remoteAddress,String area) {
		
		BaseUserEntity baseUserEntity = baseUserService.selectByMail(email);
		if(null != baseUserEntity) {
			String userId = baseUserEntity.getUserId();
			BaseRetrievePasswordEntity baseRetrievePasswordEntity = createSecurityInfo(userId,remoteAddress,area);
			MimeMessage mimeMailMessage = null;
	        try {
	            mimeMailMessage = javaMailSender.createMimeMessage();
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//	            mimeMessageHelper.setFrom(from);
	            mimeMessageHelper.setFrom(from, "");
	            mimeMessageHelper.setTo(email);
	            mimeMessageHelper.setSubject("");
	            Map<String, Object> model = new HashMap<String, Object>(5);
	            model.put("username", baseUserEntity.getUsername());
	            model.put("security_code", baseRetrievePasswordEntity.getSecurityCode());
	            model.put("security_code_create_time", baseRetrievePasswordEntity.getSecurityCodeCreateTime());
	            model.put("area", baseRetrievePasswordEntity.getArea());
	            model.put("remote_address", baseRetrievePasswordEntity.getRemoteAddress());
	            Template template = configuration.getTemplate("mail_templates/retrieve_password.ftl");
	            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	            mimeMessageHelper.setText(text, true);
	            javaMailSender.send(mimeMailMessage);
	            return SEND_STATE_SUCCESS;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return SEND_STATE_FAIL;
	        }
		}else {
			return SEND_STATE_ERROR_MAIL;
		}
	}
	
	/**
	 * @Description 生成一个验证码
	 * @param userId
	 * @param remoteAddress
	 * @param area
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 14:49:59
	 */
	public BaseRetrievePasswordEntity createSecurityInfo(String userId,String remoteAddress,String area) {
		
		int i =  0;
		String retrievePasswordId = getRetrievePasswordIdByUserId(userId);
		BaseRetrievePasswordEntity baseRetrievePasswordEntity = new BaseRetrievePasswordEntity();
		baseRetrievePasswordEntity.setUserId(userId);
		baseRetrievePasswordEntity.setSecurityCodeCreateTime(new Date());
		baseRetrievePasswordEntity.setSecurityCode(createRandom(4));
		baseRetrievePasswordEntity.setRemoteAddress(remoteAddress);
		baseRetrievePasswordEntity.setArea(area);
		if(null == retrievePasswordId) {
			baseRetrievePasswordEntity.setRetrievePasswordId(IDUtil.getId());
			i =  baseRetrievePasswordMapper.insert(baseRetrievePasswordEntity);
		}else {
			baseRetrievePasswordEntity.setRetrievePasswordId(retrievePasswordId);
			i =  baseRetrievePasswordMapper.update(baseRetrievePasswordEntity);
		}
		return (i == 1)?baseRetrievePasswordEntity:null;
	}
	
	/**
	 * @Description 生成指定位数的随机数
	 * @param length
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:11:28
	 */
	private String createRandom(int length) {
		
		Random random = new Random();
		String result="";
		for (int i=0; i<length; i++)
		{
			result+=random.nextInt(10);
		}
		return result;
	}
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	public PageInfo<BaseRetrievePasswordEntity> selectPagination(Integer page, Integer rows, BaseRetrievePasswordEntity baseRetrievePasswordEntity) {
		
		PageHelper.startPage(page, rows);
		//PageHelper.orderBy("UPDATE_TIME DESC");
		List<BaseRetrievePasswordEntity> list = baseRetrievePasswordMapper.selectPagination(baseRetrievePasswordEntity);
		PageInfo<BaseRetrievePasswordEntity> pi = new PageInfo<BaseRetrievePasswordEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param retrievePasswordId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	public BaseRetrievePasswordEntity getByPkey(String retrievePasswordId){
	
		return baseRetrievePasswordMapper.selectByPkey(retrievePasswordId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	public int insert(BaseRetrievePasswordEntity baseRetrievePasswordEntity) {
		
		return baseRetrievePasswordMapper.insert(baseRetrievePasswordEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	public int update(BaseRetrievePasswordEntity baseRetrievePasswordEntity){
	
		return baseRetrievePasswordMapper.update(baseRetrievePasswordEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param retrievePasswordId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	public int delete(String retrievePasswordId){
	
		return baseRetrievePasswordMapper.delete(retrievePasswordId);
	}
	
	/**
	 * @Description 通过用户编号查找找回密码信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 14:09:30
	 */
	public String getRetrievePasswordIdByUserId(String userId) {
		
		BaseRetrievePasswordEntity baseRetrievePasswordEntity = baseRetrievePasswordMapper.selectByUserId(userId);
		return (null == baseRetrievePasswordEntity)?null:baseRetrievePasswordEntity.getRetrievePasswordId();
	}
	
	/**
	 * @Description 验证验证码信息
	 * @param userId 用户编号
	 * @param securityCode 验证码
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 15:58:56
	 */
	public String verifySecurityCodeByUserId(String userId,String securityCode) {
		
		BaseRetrievePasswordEntity baseRetrievePasswordEntity = new BaseRetrievePasswordEntity();
		
		baseRetrievePasswordEntity.setUserId(userId);
		baseRetrievePasswordEntity.setSecurityCode(securityCode);
		return baseRetrievePasswordMapper.verifySecurityCodeByUserId(baseRetrievePasswordEntity);
	}
	
	/**
	 * @Description 验证验证码信息
	 * @param email 邮件地址
	 * @param securityCode 验证码
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 15:58:39
	 */
	public boolean verifySecurityCodeByEmail(String email,String securityCode) {
		
		String retrievePasswordId = null;
		BaseUserEntity baseUserEntity = baseUserService.selectByMail(email);
		
		if(null != baseUserEntity) {
			String userId = baseUserEntity.getUserId();
			retrievePasswordId = verifySecurityCodeByUserId(userId,securityCode);
		}
		return (null == retrievePasswordId)?false:true;
	}
	
	/**
	 * @Description 更新验证码使用时间
	 * @param retrievePasswordId 
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 16:26:33
	 */
	public int updateSecurityCodeUseTimeByRetrievePasswordId(String retrievePasswordId) {
		
		BaseRetrievePasswordEntity baseRetrievePasswordEntity = new BaseRetrievePasswordEntity();
		baseRetrievePasswordEntity.setRetrievePasswordId(retrievePasswordId);
		baseRetrievePasswordEntity.setSecurityCodeUseTime(new Date());
		return baseRetrievePasswordMapper.updateSecurityCodeUseTimeByRetrievePasswordId(baseRetrievePasswordEntity);
	}
	
}