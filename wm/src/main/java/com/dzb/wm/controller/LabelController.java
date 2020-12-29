package com.dzb.wm.controller;

import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.LabelEntity;
import com.dzb.wm.service.DropDownBoxService;
import com.dzb.wm.service.LabelService;
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
@RequestMapping("lc")
public class LabelController {

    private static final Logger log = LoggerFactory.getLogger(LabelController.class);

    @Autowired
    private LabelService labelService;

    @SysLog(moduleName = "标签分类管理", submoduleName = "初始化列表页面", params = {})
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    public String load(Model model) {

        log.debug("初始化列表页面");
        return "wm/t_label_load";
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "查询列表", params = {"offset","limit","labelEntity"})
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<LabelEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,
                                         @RequestParam(value="limit",required=false,defaultValue="10") int limit, LabelEntity labelEntity) {

        return labelService.getPagination(offset, limit ,labelEntity);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "保存新增信息", params = {"labelEntity"})
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(LabelEntity labelEntity) {

        return labelService.insert(labelEntity);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "通过主键获取信息", params = {"labelId"})
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public LabelEntity view(@RequestParam("labelId") long labelId) {

        return labelService.getByPkey(labelId);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "保存编辑信息", params = {"labelEntity"})
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public int edit(LabelEntity labelEntity) {

        return labelService.update(labelEntity);
    }


    @SysLog(moduleName = "标签分类管理", submoduleName = "删除信息", params = {"labelId"})
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public int del(@RequestParam("labelId") long labelId) {

        return labelService.del(labelId);
    }

    @SysLog(moduleName = "标签分类管理", submoduleName = "查询列表", params = {"belongPageType","dropDownBoxId"})
    @RequestMapping(value = "/getLabels",method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getLabels(@RequestParam("belongPageType") Integer belongPageType,
                               @RequestParam(value = "dropDownBoxId",required = false) Long dropDownBoxId) {
        if (belongPageType==null){
            return new ArrayList<>();
        }
        return labelService.getLabels(belongPageType,dropDownBoxId);
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public int updateState( @RequestParam(value = "labelId") Long labelId,@RequestParam(value = "mark") Integer mark){
        return labelService.updateState(labelId,mark);
    }
}
