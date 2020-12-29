package com.hymake.mobileWeb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hymake.mobileWeb.entity.NewsEntity;
import com.hymake.mobileWeb.entity.PosterItemEntity;
import com.hymake.mobileWeb.service.NewsService;
import com.hymake.mobileWeb.service.PosterItemService;

/**
 * @Description 首页
 * @author daizb@hymake.com
 * @date 2020-06-29 09:19:43
 */
@Controller
public class IndexController {
	
	@Autowired
	private PosterItemService posterItemService;
	@Autowired
	private NewsService newsService;

	/*首页*/
	@RequestMapping("/")
    public String index(Model model) {
		
		List<PosterItemEntity> posterItemsList = posterItemService.getByType("TP");
		NewsEntity headlineNewEntity = newsService.getHeadlineNew();
		List<NewsEntity> newsEntity = newsService.getTopNews();
		model.addAttribute("posterItems", posterItemsList);
		model.addAttribute("headlineNew", headlineNewEntity);
		model.addAttribute("news", newsEntity);
    	return "index";
    }
}
