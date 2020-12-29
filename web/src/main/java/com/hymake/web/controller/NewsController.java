package com.hymake.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageInfo;
import com.hymake.web.entity.NewsEntity;
import com.hymake.web.service.NewsService;

@Controller
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	/*新闻列表*/
	@GetMapping("")
    public String list(@RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="type",required=false,defaultValue="0") String type, Model model) {
		
		int limit = 10;
		PageInfo<NewsEntity> pi = newsService.getPagination((page - 1)*limit, limit, type);
		model.addAttribute("news", pi.getList());
		//model.addAttribute("total", (pi.getTotal()/limit) + 1);
		model.addAttribute("total", pi.getPages());
		model.addAttribute("offset", page);
		model.addAttribute("type", type);
		return "news/list";
	}

	/*新闻详情*/
	@GetMapping("details/{newsId}")
    public String view(@PathVariable(value="newsId") long newsId, Model model) {
		NewsEntity newsEntity = newsService.getNew(newsId);
		model.addAttribute("news", newsEntity);
		return "news/details";
	}

}
