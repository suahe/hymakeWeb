package com.hymake.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("solution")
public class SolutionController {

    /*解决方案*/
    @GetMapping("")
    public String list(Model model) {
        return "solution/list";
    }

    /*解决方案详情页 begin*/
    /*BIM全过程咨询解决方案详情页*/
    @GetMapping("BIMWholeProcessConsultingSolution")
    public String bimProcessConsulfation(Model model) {
        return "solution/details/BIMWholeProcessConsultingSolution";
    }

    /*BIM量价一体化解决方案详情页*/
    @GetMapping("BIMVolumeAndPriceIntegrationSolution")
    public String bimGuantityPrice(Model model) {
        return "solution/details/BIMVolumeAndPriceIntegrationSolution";
    }
    /*建设成本管控解决方案详情页*/
    @GetMapping("constructionCostControlSolution")
    public String constructionCostControl(Model model) {
        return "solution/details/constructionCostControlSolution";
    }
    /*电子交易解决方案详情页*/
    @GetMapping("electronicTradingSolution")
    public String electronicTransaction(Model model) {
        return "solution/details/electronicTradingSolution";
    }
    /*公共资源交易解决方案详情页*/
    @GetMapping("publicResourceTradingSolution")
    public String publicResourceTransaction(Model model) {
        return "solution/details/publicResourceTradingSolution";
    }
    /*智慧工地解决方案详情页*/
    @GetMapping("smartConstructionSiteSolution")
    public String smartSite(Model model) {
        return "solution/details/smartConstructionSiteSolution";
    }
    /*商品房监管解决方案详情页*/
    @GetMapping("commercialHousingSupervisionSolution")
    public String commercialHouseSupervision(Model model) {
        return "solution/details/commercialHousingSupervisionSolution";
    }
    /*智慧安居解决方案详情页*/
    @GetMapping("smartSettlementSolution")
    public String liveInWisdom(Model model) {
        return "solution/details/smartSettlementSolution";
    }
    /*房屋大数据解决方案详情页*/
    @GetMapping("housingBigDataSolution")
    public String houseBigData(Model model) {
        return "solution/details/housingBigDataSolution";
    }
    /*智慧教育解决方案详情页*/
    @GetMapping("smartEducationSolution")
    public String wisdomEducation(Model model) {
        return "solution/details/smartEducationSolution";
    }
    /*智慧建设解决方案详情页*/
    @GetMapping("smartConstructionSolution")
    public String wisdomConstruction(Model model) {
        return "solution/details/smartConstructionSolution";
    }
    /*智慧小区解决方案详情页*/
    @GetMapping("smartCommunitySolution")
    public String smartCommunity(Model model) {
        return "solution/details/smartCommunitySolution";
    }
    /*智慧园区解决方案详情页*/
    @GetMapping("smartParkSolution")
    public String smartPark(Model model) {
        return "solution/details/smartParkSolution";
    }
    /*智慧城市咨询解决方案详情页*/
    @GetMapping("smartCityConsultingSolution")
    public String smartCityConsult(Model model) {
        return "solution/details/smartCityConsultingSolution";
    }
    /*解决方案详情页 end*/

}
