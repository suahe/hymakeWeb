package com.hymake.mobileWeb.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.NewsEntity;
import com.hymake.mobileWeb.service.NewsService;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	/*新闻列表*/
	@GetMapping("")
    public String list(@RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="type",required=false,defaultValue="0") String type, Model model) {
		if(StringUtils.isEmpty(type) ||(!"1".equals(type)&&!"2".equals(type)&&!"3".equals(type)&&!"4".equals(type))){
			type = "1";
		}
		model.addAttribute("type", type);
		return "news/list";
	}

	/*新闻详情*/
	@GetMapping("details/{newsId}")
    public String view(@PathVariable(value="newsId") long newsId, Model model) {
		NewsEntity newsEntity = newsService.getNew(newsId);
		model.addAttribute("detailTitle",newsEntity!=null?newsEntity.getNewTypeName():"");
		model.addAttribute("news", newsEntity);
		return "news/details";
	}


	/*新闻分页*/
	@ResponseBody
	@PostMapping("pageList")
	public Map pageList(@RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="type",required=false,defaultValue="0") String type) {
		int limit = 10;
		PageInfo<NewsEntity> pi = newsService.getPagination((page - 1)*limit, limit, type);
		Map<Object, Object> resDto = new HashMap<>();
		resDto.put("list",pi.getList());
		resDto.put("pages",pi.getPages());
		return resDto;
	}
}
