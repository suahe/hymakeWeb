package com.dzb.wm.controller;

import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.DropDownBoxEntity;
import com.dzb.wm.service.DropDownBoxService;
import com.dzb.wm.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author suahe
 * @date 2020/11/11 14:17
 * @describe TODO
 */
@Controller
@RequestMapping("ddbc")
public class DropDownBoxController {

    private static final Logger log = LoggerFactory.getLogger(DropDownBoxController.class);

    @Autowired
    private DropDownBoxService dropDownBoxService;

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "初始化列表页面", params = {})
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    public String load(Model model) {

        log.debug("初始化列表页面");
        return "wm/t_drop_down_box_load";
    }

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "查询列表", params = {"offset","limit","dropDownBoxEntity"})
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<DropDownBoxEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,
                                         @RequestParam(value="limit",required=false,defaultValue="10") int limit, DropDownBoxEntity dropDownBoxEntity) {

        return dropDownBoxService.getPagination(offset, limit ,dropDownBoxEntity);
    }

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "保存新增信息", params = {"dropDownBoxEntity"})
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(DropDownBoxEntity dropDownBoxEntity) {

        return dropDownBoxService.insert(dropDownBoxEntity);
    }

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "通过主键获取信息", params = {"dropDownBoxId"})
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public DropDownBoxEntity view(@RequestParam("dropDownBoxId") long dropDownBoxId,Model model) {
        DropDownBoxEntity dropDownBoxEntity = dropDownBoxService.getByPkey(dropDownBoxId);
        model.addAttribute("dropDownBoxEntity",dropDownBoxEntity);
        return dropDownBoxEntity;
    }

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "保存编辑信息", params = {"dropDownBoxEntity"})
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public int edit(DropDownBoxEntity dropDownBoxEntity) {

        return dropDownBoxService.update(dropDownBoxEntity);
    }


    @SysLog(moduleName = "下拉框分类管理", submoduleName = "删除信息", params = {"dropDownBoxId"})
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public int del(@RequestParam("dropDownBoxId") long dropDownBoxId) {

        return dropDownBoxService.del(dropDownBoxId);
    }

    @SysLog(moduleName = "下拉框分类管理", submoduleName = "修改发布状态", params = {"dropDownBoxId"})
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public int updateState( @RequestParam(value = "dropDownBoxId") Long dropDownBoxId,@RequestParam(value = "mark") Integer mark){
        return dropDownBoxService.updateState(dropDownBoxId,mark);
    }
}
