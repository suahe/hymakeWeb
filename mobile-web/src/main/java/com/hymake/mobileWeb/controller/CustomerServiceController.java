package com.hymake.mobileWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/13 11:11.
 * @Description: 客户服务
 */
@Controller
@RequestMapping("customerService")
public class CustomerServiceController {

    /*客户服务*/
    @GetMapping("")
    public String customerService(Model model) {
        return "customerService/list";
    }

}
