package com.hymake.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/13 11:11.
 * @Description: 用户服务
 */
@Controller
@RequestMapping("userService")
public class UserServiceController {

    /*客服QQ*/
    @GetMapping("customerService")
    public String customerService(Model model) {
        return "userService/customerService";
    }

    /*资源下载*/
    @GetMapping("resourceDownload")
    public String resourceDownload(Model model) {
        return "userService/resourceDownload";
    }

    /*常见问题*/
    @GetMapping("commonProblem")
    public String commonProblem(Model model) {
        return "userService/commonProblem";
    }
}
