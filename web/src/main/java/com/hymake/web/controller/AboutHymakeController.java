package com.hymake.web.controller;

import com.hymake.web.entity.ChronologyEntity;
import com.hymake.web.entity.ChronologyGroup;
import com.hymake.web.service.ChronologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


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

    @GetMapping({"intoHymake"})
    public String enterHymake(Model model) {
        return "aboutHymake/intoHymake";
    }

    @GetMapping({"previousEvents"})
    public String previousMatch(Model model) {
        return "aboutHymake/previousEvents";
    }

    @GetMapping({"growthProcess"})
    public String growthProcess(Model model) {
        List<ChronologyEntity> ChronologyEntityList = this.chronologyService.getAll();
        List<ChronologyGroup> chronologyGroupList = this.chronologyService.getYearGroupList(ChronologyEntityList);
        model.addAttribute("chronologyGroupList", chronologyGroupList);
        return "aboutHymake/growthProcess";
    }

    @GetMapping({"contactUs"})
    public String contactWe(Model model) {
        return "aboutHymake/contactUs";
    }

    @GetMapping({"followHymake"})
    public String followHymake(Model model) {
        return "aboutHymake/followHymake";
    }
}

