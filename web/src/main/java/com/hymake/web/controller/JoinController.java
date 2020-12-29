package com.hymake.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hymake.web.entity.JoinEntity;
import com.hymake.web.service.JoinService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description t_join 表Controller类
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("joinHymake")
public class JoinController{

	private static final Logger log = LoggerFactory.getLogger(JoinController.class);

	@Autowired
	private JoinService joinService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String list(Model model,@RequestParam(value="joinType",required=false,defaultValue="0") String joinType) {
		
		log.debug("初始化列表页面");
		JoinEntity joinEntity = new JoinEntity();
		joinEntity.setJoinType(joinType);
		List<JoinEntity> join = joinService.getPagination(joinEntity);
		model.addAttribute("join", join);
		model.addAttribute("joinType", joinType);
		return "joinHymake/list";
	}

}