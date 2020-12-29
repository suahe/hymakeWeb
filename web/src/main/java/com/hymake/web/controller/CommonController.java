package com.hymake.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共处理控制器
 */
@Controller
@RequestMapping("common")
public class CommonController {

    @GetMapping("browserTips")
    public String list() {
        return "common/browserTips";
    }

    @RequestMapping("error-403")
    public String error403() {
        return "error/403";
    }

    @RequestMapping("error-404")
    public String error404() {
        return "error/404";
    }

    @RequestMapping("error-500")
    public String error500() {
        return "error/500";
    }

}
