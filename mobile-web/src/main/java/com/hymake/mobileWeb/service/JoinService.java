package com.hymake.mobileWeb.service;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.service.BaseTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hymake.mobileWeb.entity.JoinEntity;
import com.hymake.mobileWeb.mapper.JoinMapper;

/**
 * @Description t_join 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */

@Service("JoinService")
@Transactional
public class JoinService {

	private static final Logger log = LoggerFactory.getLogger(JoinService.class);
	
	@Autowired
	private JoinMapper joinMapper;
	@Autowired
	private BaseTypeService baseTypeService;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public PageInfo<JoinEntity> getPagination(Integer offset, Integer limit,JoinEntity joinEntity) {
		PageHelper.offsetPage(offset, limit);
		PageInfo<JoinEntity> pi = new PageInfo<JoinEntity>(joinMapper.selectPagination(joinEntity));
		List<JoinEntity> list = pi.getList();
		list = baseTypeService.translate(list, "ZPLX", "joinType", "joinTypeName");
		list = baseTypeService.translate(list, "GZDD", "workplace", "workplaceName");
		pi.setList(list);
		return pi;
	}
	
}