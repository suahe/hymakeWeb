package com.hymake.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suahe
 * @date 2020/10/9 9:09
 * @describe 苏阿合
 */
@Controller
@RequestMapping("/cityConstruction")
public class CityConstructionController {

    @GetMapping("/trackSystem")
    public String trackSystem(Model model) {
        return "cityConstruction/trackSystem";
    }
}
