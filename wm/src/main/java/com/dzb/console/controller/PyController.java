package com.dzb.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.service.PyService;
import com.dzb.console.syslog.SysLog;

@Controller
@RequestMapping("pyc")
public class PyController {

	@Autowired
	private PyService pyService;
	
	/**
	 * @Description 删除信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	@SysLog(moduleName = "中文转拼音管理", submoduleName = "中文字符串转拼音首字母", params = {"hanzi"})
	@RequestMapping(value = "/toPyFl",method = RequestMethod.GET)
	@ResponseBody
	public String toPyFl(@RequestParam("hanzi") String hanzi) {
		
		return pyService.toPinyinFirstLetter(hanzi);
	}
}
