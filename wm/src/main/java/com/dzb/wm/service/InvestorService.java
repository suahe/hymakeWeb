package com.dzb.wm.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.InvestorEntity;
import com.dzb.wm.mapper.InvestorMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_investor 表Service类
 * @author daizb@hymake.com
 * @date 2020-08-11 19:02:47
 * @version 1.0
 * @remark create by ca
 */

@Service("InvestorService")
@Transactional
public class InvestorService {

	private static final Logger log = LoggerFactory.getLogger(InvestorService.class);
	
	@Autowired
	private InvestorMapper investorMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	public PageInfo<InvestorEntity> getPagination(Integer offset, Integer limit, InvestorEntity investorEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<InvestorEntity>(investorMapper.selectPagination(investorEntity));
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param investorId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	public InvestorEntity getByPkey(long investorId){
	
		log.debug("通过主键查询信息");
		return investorMapper.selectByPkey(investorId);
	}
	
	/**
	 * @Description 新增信息
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	public int insert(InvestorEntity investorEntity) {

		log.debug("新增信息");
		investorEntity.setInvestorId(IDUtil.getSnowflakeId());
		investorEntity.setUpdateUserid(UserInfoUtil.getUserId());
		investorEntity.setUpdateTime(new Date());
		return investorMapper.insert(investorEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	public int update(InvestorEntity investorEntity){
	
		log.debug("编辑信息");
		investorEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		investorEntity.setUpdateTime(new Date());
		return investorMapper.update(investorEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param investorId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	public int delete(long investorId){
	
		log.debug("删除信息");
		return investorMapper.delete(investorId);
	}
	
	public int updatePushTimeByInvestorId(long investorId, Date pushTime) {
		
		log.debug("发布信息");
		InvestorEntity investorEntity = new InvestorEntity();
		investorEntity.setPushTime(pushTime);
		investorEntity.setInvestorId(investorId);
		investorEntity.setUpdateUserid(UserInfoUtil.getUserId());
		investorEntity.setUpdateTime(new Date());
		return investorMapper.updatePushTimeByInvestorId(investorEntity);
	}
	
}