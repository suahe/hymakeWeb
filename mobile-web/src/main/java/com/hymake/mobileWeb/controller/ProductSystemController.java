package com.hymake.mobileWeb.controller;

import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.DropDownBoxEntity;
import com.hymake.mobileWeb.entity.LabelEntity;
import com.hymake.mobileWeb.entity.ProductEntity;
import com.hymake.mobileWeb.entity.enums.BelongPageType;
import com.hymake.mobileWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/13 10:14.
 * @Description: 产品体系
 * productSystem/coreProduct/calculationSoftware
 */
@Controller
@RequestMapping("productSystem")
public class ProductSystemController {

    @Autowired
    private ProductService productService;
    @Autowired
    private DropDownBoxService dropDownBoxService;
    @Autowired
    private LabelService labelService;


    /*产品体系*/
    @GetMapping("")
    public String list(Model model, @RequestParam(value="dropDownBoxId",required=false,defaultValue="-1") long dropDownBoxId,@RequestParam(value="labelId",required=false,defaultValue="-1") long labelId) {
        model.addAttribute("dropDownBoxId",dropDownBoxId);
        if(dropDownBoxId==-1){
            model.addAttribute("dropDownBoxName","所有产品分类");
        }else {
            DropDownBoxEntity dropDownBoxEntity = dropDownBoxService.getByPkey(dropDownBoxId);
            if(dropDownBoxEntity!=null){
                model.addAttribute("dropDownBoxName",dropDownBoxEntity.getName()!=null?dropDownBoxEntity.getName():"所有产品分类");
            }
        }
        //获取下拉框分类
        List<DropDownBoxEntity> dropDownBoxEntities = dropDownBoxService.getByBelongPageType(BelongPageType.PRODUCT_SYSTEM.getValue());
        model.addAttribute("dropDownBoxs",dropDownBoxEntities);//下拉框集合
        //获取标签分类
        model.addAttribute("labelId",labelId);
        List<LabelEntity> labels = labelService.getByDropDownIdAndBelongPageType(dropDownBoxId,BelongPageType.PRODUCT_SYSTEM.getValue());
        model.addAttribute("labels",labels);//标签集合
        return "productSystem/list";
    }

    /*产品体系列表展示*/
    @ResponseBody
    @PostMapping("productsPage")
    public Map productsPage(@RequestParam(value="page",required=false,defaultValue="1") int page,@RequestParam(value="dropDownBoxId",required=false,defaultValue="-1") long dropDownBoxId,@RequestParam(value="labelId",required=false,defaultValue="-1") long labelId){
        int limit = 15;
        PageInfo<ProductEntity> pi = productService.getPagination((page - 1)*limit, limit,
                dropDownBoxId,labelId,BelongPageType.PRODUCT_SYSTEM.getValue());
        Map<Object, Object> reDto = new HashMap<>();
        reDto.put("list",pi.getList());
        reDto.put("pages",pi.getPages());
        return reDto;
    }

    /*产品体系详情页 begin*/
    /*核心产品详情页*/
    /*计价软件详情页*/
    @GetMapping("coreProducts/priceSoftware")
    public String priceSoftware(Model model, HttpServletRequest request) {
        model.addAttribute("detailTitle","计价软件");
        return "productSystem/details/coreProducts/priceSoftware";
    }

    /*算量软件详情页*/
    @GetMapping("coreProducts/calculationSoftware")
    public String calculationSoftware(Model model, HttpServletRequest request) {
        model.addAttribute("detailTitle","算量软件");
        return "productSystem/details/coreProducts/calculationSoftware";
    }

    /*海迈BIM产品详情页*/
    @GetMapping("coreProducts/BIMProduct")
    public String bimProduct(Model model, HttpServletRequest request) {
        model.addAttribute("detailTitle","BIM产品");
        return "productSystem/details/coreProducts/BIMProduct";
    }

    /*造价会员制平台情页*/
    @GetMapping("coreProducts/costMembership")
    public String costMembership(Model model) {
        model.addAttribute("detailTitle","造价会员制平台");
        return "productSystem/details/coreProducts/costMembership";
    }

    /*基础产品详情页*/
    /*招投标评估评审软件详情页*/
    @GetMapping("basicProducts/biddingEvaluationReviewSoftware")
    public String bidEvaluationSoftware(Model model) {
        model.addAttribute("detailTitle","招投标评估评审软件");
        return "productSystem/details/basicProducts/biddingEvaluationReviewSoftware";
    }

    /*电子交易平台详情页*/
    @GetMapping("basicProducts/electronicTradingPlatform")
    public String electronicTrade(Model model) {
        model.addAttribute("detailTitle","电子交易平台");
        return "productSystem/details/basicProducts/electronicTradingPlatform";
    }

    /*交易服务平台详情页*/
    @GetMapping("basicProducts/tradingServicePlatform")
    public String tradeService(Model model) {
        model.addAttribute("detailTitle","交易服务平台");
        return "productSystem/details/basicProducts/tradingServicePlatform";
    }

    /*工程建设监管系统详情页*/
    @GetMapping("basicProducts/engineeringConstructionSupervisionPlatform")
    public String engineereConstructionSupervision(Model model) {
        model.addAttribute("detailTitle","工程建设监管平台");
        return "productSystem/details/basicProducts/engineeringConstructionSupervisionPlatform";
    }

    /*扩展产品详情页*/
    /*电子档案平台详情页*/
    @GetMapping("extendedProducts/electronicFilePlatform")
    public String electronicArchive(Model model) {
        model.addAttribute("detailTitle","电子档案平台");
        return "productSystem/details/extendedProducts/electronicFilePlatform";
    }

    /*企业信用管理平台详情页*/
    @GetMapping("extendedProducts/enterpriseCreditManagementPlatform")
    public String enterpriseCredit(Model model) {
        model.addAttribute("detailTitle","企业信用管理平台");
        return "productSystem/details/extendedProducts/enterpriseCreditManagementPlatform";
    }

    /*附加产品详情页*/
    /*勘察设计管理平台详情页*/
    @GetMapping("additionalProducts/surveyAndDesignManagementPlatform")
    public String surveyDesign(Model model) {
        model.addAttribute("detailTitle","勘察设计管理平台");
        return "productSystem/details/additionalProducts/surveyAndDesignManagementPlatform";
    }

    /*项目视频监管平台详情页*/
    @GetMapping("additionalProducts/projectVideoMonitoringPlatform")
    public String projectVideoSupervision(Model model) {
        model.addAttribute("detailTitle","项目视频监管平台");
        return "productSystem/details/additionalProducts/projectVideoMonitoringPlatform";
    }

    /*潜力产品详情页*/
    /*BIM数字运维平台详情页*/
    @GetMapping("potentialProducts/BIMDigitalOperationAndMaintenancePlatform")
    public String bimDigitalOperation(Model model) {
        model.addAttribute("detailTitle","BIM数字运维平台");
        return "productSystem/details/potentialProducts/BIMDigitalOperationAndMaintenancePlatform";
    }

    /*商品房价格评估调控系统详情页*/
    @GetMapping("potentialProducts/commercialHousingPriceEvaluationAndControlSystem")
    public String commercialHouseEvaluation(Model model) {
        model.addAttribute("detailTitle","商品房价格评估调控系统");
        return "productSystem/details/potentialProducts/commercialHousingPriceEvaluationAndControlSystem";
    }
    /*产品体系详情页 end*/

    @GetMapping("getDropDownBoxList")
    @ResponseBody
    public List<DropDownBoxEntity> getDropDownBoxList() {
        return  dropDownBoxService.getByBelongPageType(BelongPageType.PRODUCT_SYSTEM.getValue());
    }
}
