package com.hymake.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/13 10:14.
 * @Description: 产品体系
 */
@Controller
@RequestMapping("productSystem")
public class ProductSystemController {

    /*产品体系*/
    @GetMapping("")
    public String list(Model model, @RequestParam(value="active",required=false,defaultValue="0") String active) {
        model.addAttribute("active",active);
        return "productSystem/list";
    }

    /*产品体系详情页 begin*/
    /*核心产品详情页*/
    /*计价软件详情页*/
    @GetMapping("coreProducts/priceSoftware")
    public String priceSoftware(Model model) {
        return "productSystem/coreProducts/priceSoftware";
    }

    /*算量软件详情页*/
    @GetMapping("coreProducts/calculationSoftware")
    public String calculationSoftware(Model model) {
        return "productSystem/coreProducts/calculationSoftware";
    }

    /*算量软件详情页*/
    @GetMapping("coreProducts/BIMProduct")
    public String bimProduce(Model model) {
        return "productSystem/coreProducts/BIMProduct";
    }

    /*造价会员制平台情页*/
    @GetMapping("coreProducts/costMembership")
    public String costMembership(Model model) {
        return "productSystem/coreProducts/costMembership";
    }

    /*基础产品详情页*/
    /*招投标评估评审软件详情页*/
    @GetMapping("basicProducts/biddingEvaluationReviewSoftware")
    public String bidEvaluationSoftware(Model model) {
        return "productSystem/basicProducts/biddingEvaluationReviewSoftware";
    }

    /*电子交易平台详情页*/
    @GetMapping("basicProducts/electronicTradingPlatform")
    public String electronicTrade(Model model) {
        return "productSystem/basicProducts/electronicTradingPlatform";
    }

    /*交易服务平台详情页*/
    @GetMapping("basicProducts/tradingServicePlatform")
    public String tradeService(Model model) {
        return "productSystem/basicProducts/tradingServicePlatform";
    }

    /*工程建设监管系统详情页*/
    @GetMapping("basicProducts/engineeringConstructionSupervisionPlatform")
    public String engineereConstructionSupervision(Model model) {
        return "productSystem/basicProducts/engineeringConstructionSupervisionPlatform";
    }

    /*扩展产品详情页*/
    /*电子档案平台详情页*/
    @GetMapping("extendedProducts/electronicFilePlatform")
    public String electronicArchive(Model model) {
        return "productSystem/extendedProducts/electronicFilePlatform";
    }

    /*企业信用管理平台详情页*/
    @GetMapping("extendedProducts/enterpriseCreditManagementPlatform")
    public String enterpriseCredit(Model model) {
        return "productSystem/extendedProducts/enterpriseCreditManagementPlatform";
    }

    /*附加产品详情页*/
    /*勘察设计管理平台详情页*/
    @GetMapping("additionalProducts/surveyAndDesignManagementPlatform")
    public String surveyDesign(Model model) {
        return "productSystem/additionalProducts/surveyAndDesignManagementPlatform";
    }

    /*项目视频监管平台详情页*/
    @GetMapping("additionalProducts/projectVideoMonitoringPlatform")
    public String projectVideoSupervision(Model model) {
        return "productSystem/additionalProducts/projectVideoMonitoringPlatform";
    }

    /*潜力产品详情页*/
    /*BIM数字运维平台详情页*/
    @GetMapping("potentialProducts/BIMDigitalOperationAndMaintenancePlatform")
    public String bimDigitalOperation(Model model) {
        return "productSystem/potentialProducts/BIMDigitalOperationAndMaintenancePlatform";
    }

    /*商品房价格评估调控系统详情页*/
    @GetMapping("potentialProducts/commercialHousingPriceEvaluationAndControlSystem")
    public String commercialHouseEvaluation(Model model) {
        return "productSystem/potentialProducts/commercialHousingPriceEvaluationAndControlSystem";
    }
    /*产品体系详情页 end*/
}
