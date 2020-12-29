package com.dzb.wm.controller;

import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.InvestorEntity;
import com.dzb.wm.entity.ProductEntity;
import com.dzb.wm.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author suahe
 * @date 2020/11/11 14:17
 * @describe TODO
 */
@Controller
@RequestMapping("pc")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @SysLog(moduleName = "产品管理", submoduleName = "初始化列表页面", params = {})
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    public String load(Model model) {

        log.debug("初始化列表页面");
        return "wm/t_product_load";
    }

    @SysLog(moduleName = "产品管理", submoduleName = "查询列表", params = {"offset","limit","productEntity"})
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<ProductEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,
                                         @RequestParam(value="limit",required=false,defaultValue="10") int limit, ProductEntity productEntity) {

        return productService.getPagination(offset, limit ,productEntity);
    }

    @SysLog(moduleName = "产品管理", submoduleName = "保存新增信息", params = {"productEntity"})
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(ProductEntity productEntity) {

        return productService.insert(productEntity);
    }

    @SysLog(moduleName = "产品管理", submoduleName = "通过主键获取信息", params = {"productId"})
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public ProductEntity view(@RequestParam("productId") long productId) {

        return productService.getByPkey(productId);
    }

    @SysLog(moduleName = "产品管理", submoduleName = "保存编辑信息", params = {"productEntity"})
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public int edit(ProductEntity productEntity) {

        return productService.update(productEntity);
    }


    @SysLog(moduleName = "产品管理", submoduleName = "删除信息", params = {"productId"})
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public int del(@RequestParam("productId") long productId) {

        return productService.del(productId);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "查询列表", params = {"belongPageType","labelId"})
    @RequestMapping(value = "/getProductsByLabelId",method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getProductsByLabelId(@RequestParam("belongPageType") Integer belongPageType,
                               @RequestParam(value = "labelId",required = false) Long labelId) {
        if (belongPageType==null){
            return new ArrayList<>();
        }
        return productService.getProductsByLabelId(belongPageType,labelId);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "查询列表", params = {"belongPageType","labelId"})
    @RequestMapping(value = "/getProductsByDropDownBoxId",method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getProductsByDropDownBoxId(@RequestParam("belongPageType") Integer belongPageType,
                                 @RequestParam(value = "dropDownBoxId",required = false) Long dropDownBoxId) {
        if (belongPageType==null){
            return new ArrayList<>();
        }
        return productService.getProductsByDropDownBoxId(belongPageType,dropDownBoxId);
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public int updateState( @RequestParam(value = "productId") Long productId,@RequestParam(value = "mark") Integer mark){
        return productService.updateState(productId,mark);
    }
}
