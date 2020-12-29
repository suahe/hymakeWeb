package com.hymake.mobileWeb.controller;


import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.LabelEntity;
import com.hymake.mobileWeb.entity.ProductEntity;
import com.hymake.mobileWeb.entity.enums.BelongPageType;
import com.hymake.mobileWeb.service.LabelService;
import com.hymake.mobileWeb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("solution")
public class SolutionController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LabelService labelService;

    /*解决方案*/
    @GetMapping("")
    public String list(Model model,@RequestParam(value="labelId",required=false,defaultValue="-1") long labelId) {
        model.addAttribute("labelId",labelId);
        List<LabelEntity> labels = labelService.getByBelongPageType(BelongPageType.SOLUTION.getValue());
        model.addAttribute("labels",labels);
        return "solution/list";
    }

    /*产品体系列表展示*/
    @ResponseBody
    @PostMapping("solutionsPage")
    public Map solutionsPage(@RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="labelId",required=false,defaultValue="-1") long labelId){
        int limit = 15;
        PageInfo<ProductEntity> pi = productService.getPagination((page - 1)*limit, limit,
                null,labelId,BelongPageType.SOLUTION.getValue());
        Map<Object, Object> reDto = new HashMap<>();
        reDto.put("list",pi.getList());
        reDto.put("pages",pi.getPages());
        return reDto;
    }

    /*BIM全过程咨询解决方案详情页*/
    @GetMapping("BIMWholeProcessConsultingSolution")
    public String bimProcessConsulfation(Model model) {
        model.addAttribute("detailTitle","BIM全过程咨询解决方案");
        return "solution/details/BIMWholeProcessConsultingSolution";
    }

    /*BIM量价一体化解决方案详情页*/
    @GetMapping("BIMVolumeAndPriceIntegrationSolution")
    public String bimGuantityPrice(Model model) {
        model.addAttribute("detailTitle","BIM量价一体化解决方案");
        return "solution/details/BIMVolumeAndPriceIntegrationSolution";
    }

    /*建设成本管控解决方案详情页*/
    @GetMapping("constructionCostControlSolution")
    public String constructionCostControl(Model model) {
        model.addAttribute("detailTitle","建设成本管控解决方案");
        return "solution/details/constructionCostControlSolution";
    }

    /*电子交易解决方案详情页*/
    @GetMapping("electronicTradingSolution")
    public String electronicTransaction(Model model) {
        model.addAttribute("detailTitle","电子交易解决方案");
        return "solution/details/electronicTradingSolution";
    }

    /*公共资源交易解决方案详情页*/
    @GetMapping("publicResourceTradingSolution")
    public String publicResourceTransaction(Model model) {
        model.addAttribute("detailTitle","公共资源交易解决方案");
        return "solution/details/publicResourceTradingSolution";
    }

    /*智慧工地解决方案详情页*/
    @GetMapping("smartConstructionSiteSolution")
    public String smartSite(Model model) {
        model.addAttribute("detailTitle","智慧工地解决方案");
        return "solution/details/smartConstructionSiteSolution";
    }

    /*商品房监管解决方案详情页*/
    @GetMapping("commercialHousingSupervisionSolution")
    public String commercialHouseSupervision(Model model) {
        model.addAttribute("detailTitle","商品房监管解决方案");
        return "solution/details/commercialHousingSupervisionSolution";
    }

    /*智慧安居解决方案详情页*/
    @GetMapping("smartSettlementSolution")
    public String liveInWisdom(Model model) {
        model.addAttribute("detailTitle","智慧安居解决方案");
        return "solution/details/smartSettlementSolution";
    }

    /*房屋大数据解决方案详情页*/
    @GetMapping("housingBigDataSolution")
    public String houseBigData(Model model) {
        model.addAttribute("detailTitle","房屋大数据解决方案");
        return "solution/details/housingBigDataSolution";
    }

    /*智慧教育解决方案详情页*/
    @GetMapping("smartEducationSolution")
    public String wisdomEducation(Model model) {
        model.addAttribute("detailTitle","智慧教育解决方案");
        return "solution/details/smartEducationSolution";
    }

    /*智慧建设解决方案详情页*/
    @GetMapping("smartConstructionSolution")
    public String wisdomConstruction(Model model) {
        model.addAttribute("detailTitle","智慧建设解决方案");
        return "solution/details/smartConstructionSolution";
    }

    /*智慧小区解决方案详情页*/
    @GetMapping("smartCommunitySolution")
    public String smartCommunity(Model model) {
        model.addAttribute("detailTitle","智慧小区解决方案");
        return "solution/details/smartCommunitySolution";
    }

    /*智慧园区解决方案详情页*/
    @GetMapping("smartParkSolution")
    public String smartPark(Model model) {
        model.addAttribute("detailTitle","智慧园区解决方案");
        return "solution/details/smartParkSolution";
    }

    /*智慧城市咨询解决方案详情页*/
    @GetMapping("smartCityConsultingSolution")
    public String smartCityConsult(Model model) {
        model.addAttribute("detailTitle","智慧城市咨询解决方案");
        return "solution/details/smartCityConsultingSolution";
    }

    @GetMapping("getLabelList")
    @ResponseBody
    public List<LabelEntity> getLabelList() {
        return  labelService.getByBelongPageType(BelongPageType.SOLUTION.getValue());
    }
}
