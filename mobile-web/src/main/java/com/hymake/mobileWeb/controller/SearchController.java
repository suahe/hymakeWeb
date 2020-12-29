package com.hymake.mobileWeb.controller;

import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.SearchEntity;
import com.hymake.mobileWeb.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suahe
 * @date 2020/11/2 14:40
 * @describe 搜索Controller
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /*搜索页面*/
    @GetMapping("")
    public String list(Model model,@RequestParam(value="keyword",required=false) String keyword) {
        model.addAttribute("keyword",keyword==null?"":keyword);
        return "search/list";
    }

    /*产品体系列表展示*/
    @ResponseBody
    @PostMapping("searchsPage")
    public Map searchsPage(@RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="keyword",required=false) String keyword){
        int limit = 10;
        Map<Object, Object> reDto = new HashMap<>();
        if(StringUtils.isEmpty(keyword)){
            reDto.put("list",new ArrayList<SearchEntity>());
            reDto.put("pages",0);
            return reDto;
        }
        PageInfo<SearchEntity> pi = searchService.search((page - 1)*limit, limit, keyword);
        reDto.put("list",pi.getList());
        reDto.put("pages",pi.getPages());
        return reDto;
    }
}
