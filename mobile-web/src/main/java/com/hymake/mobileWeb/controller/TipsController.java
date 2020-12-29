package com.hymake.mobileWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suahe
 * @date 2020/9/14 17:01
 * @describe 提示页面处理
 */
@RequestMapping("tips")
@Controller
public class TipsController {

    /**
     * 低版本浏览器提示页面
     */
    @GetMapping("lowie")
    public String list() {
        return "tips/lowie";
    }
}
