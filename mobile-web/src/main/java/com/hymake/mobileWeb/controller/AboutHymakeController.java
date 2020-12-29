package com.hymake.mobileWeb.controller;

import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.ChronologyEntity;
import com.hymake.mobileWeb.entity.JoinEntity;
import com.hymake.mobileWeb.service.ChronologyService;
import com.hymake.mobileWeb.service.JoinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: suahe.
 * @Date:Created in 2020/7/6 13:36.
 * @Description: 关于海迈
 */
@Controller
@RequestMapping("aboutHymake")
public class AboutHymakeController {

    @Autowired
    private ChronologyService chronologyService;
    @Autowired
    private JoinService joinService;

    /**
     * 走进海迈
     */
    @GetMapping({"intoHymake"})
    public String enterHymake(Model model) {
        return "aboutHymake/intoHymake";
    }

    /**
     * 历届赛事
     */
    @GetMapping({"previousEvents"})
    public String previousMatch(Model model) {
        return "aboutHymake/previousEvents";
    }

    /**
     * 联系我们
     */
    @GetMapping({"contactUs"})
    public String contactWe(Model model) {
        return "aboutHymake/contactUs";
    }

    /**
     * 关注海迈
     * @param model
     * @return
     */
    @GetMapping({"followHymake"})
    public String followHymake(Model model) {
        return "aboutHymake/followHymake";
    }

    /**
     * 成长历程
     */
    @GetMapping({"chronology"})
    public String chronology(Model model,@RequestParam(value="year",required=false) String year) {
        List<String> years = new ArrayList<>();//显示数据对应年分
        List<String> downYears = new ArrayList<>();//下拉框年份
        downYears = chronologyService.getYears();
        if(StringUtils.isEmpty(year)){
            years = downYears;
        }else {
            years.add(year);
        }
        model.addAttribute("downYears", downYears);
        model.addAttribute("years", years);
        return "aboutHymake/chronology";
    }

    /*成长历程分页*/
    @ResponseBody
    @PostMapping("chronologysPage")
    public Map chronologysPage(@RequestParam(value="year",required=false,defaultValue="2020") String year) {
        List<ChronologyEntity> chronologyEntities = chronologyService.getByYear(year);
        Map<Object, Object> resDto = new HashMap<>();
        resDto.put("list",chronologyEntities);
        resDto.put("year",year);
        return resDto;
    }


    /**
     * 加入海迈
     */
    @GetMapping({"joinHymake"})
    public String joinHymake(Model model) {
        model.addAttribute("type","0");
        return "aboutHymake/joinHymake";
    }

    /**
     * 加入海迈分页
     */
    @ResponseBody
    @PostMapping("joinsPage")
    public Map joinPage(@RequestParam(value="page",required=false,defaultValue="1") int page,@RequestParam(value="joinType",required=false,defaultValue="0") String joinType) {
        int limit = 6;
        JoinEntity joinEntity = new JoinEntity();
        joinEntity.setJoinType(joinType);
        PageInfo<JoinEntity> pi = joinService.getPagination((page - 1)*limit, limit,joinEntity);
        Map<Object, Object> resDto = new HashMap<>();
        resDto.put("list",pi.getList());
        resDto.put("pages",pi.getPages());
        return resDto;
    }

}

