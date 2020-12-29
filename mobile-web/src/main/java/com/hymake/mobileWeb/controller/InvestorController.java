package com.hymake.mobileWeb.controller;

import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.InvestorEntity;
import com.hymake.mobileWeb.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 投资者关系管理
 */
@Controller
@RequestMapping("investorRelations")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    /**
     * 投资者关系列表
     */
    @GetMapping("")
    public String list(@RequestParam(value = "page",required = false,defaultValue = "1") int page, @RequestParam(value = "type",required = false,defaultValue = "1") String type, Model model) {
        model.addAttribute("type", type);
        return "investorRelations/list";
    }

    /**
     * 投资者关系分页
     */
    @ResponseBody
    @PostMapping("investorsPage")
    public Map pageList(@RequestParam(value = "page",required = false,defaultValue = "1") int page, @RequestParam(value = "type",required = false,defaultValue = "1") String type, Model model) {
        int limit = 10;
        PageInfo<InvestorEntity> pi = this.investorService.getPagination((page - 1) * limit, Integer.valueOf(limit), type);
        Map<Object, Object> resDto = new HashMap<>();
        resDto.put("list", pi.getList());
        resDto.put("pages", page);
        return resDto;
    }
}
