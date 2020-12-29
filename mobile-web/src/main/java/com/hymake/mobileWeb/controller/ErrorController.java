package com.hymake.mobileWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suahe
 * @date 2020/9/14 17:03
 * @describe 错误页面跳转控制器
 */
@RequestMapping("error")
@Controller
public class ErrorController {

    @GetMapping("403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("500")
    public String error500() {
        return "error/500";
    }
}
