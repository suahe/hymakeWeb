package com.hymake.web.controller;

import com.github.pagehelper.PageInfo;
import com.hymake.web.entity.InvestorEntity;
import com.hymake.web.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        int limit = 10;
        PageInfo<InvestorEntity> pi = this.investorService.getPagination((page - 1) * limit, Integer.valueOf(limit), type);
        model.addAttribute("investors", pi.getList());
        model.addAttribute("total", pi.getPages());
        model.addAttribute("offset", page);
        model.addAttribute("type", type);
        return "investorRelations/list";
    }
}
