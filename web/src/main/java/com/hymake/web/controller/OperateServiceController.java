package com.hymake.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/6 11:33.
 * @Description: 运营服务
 */
@Controller
@RequestMapping("operateService")
public class OperateServiceController {

    /*客服QQ*/
    @GetMapping("")
    public String customerService(Model model) {
        return "operateService/list";
    }




}
