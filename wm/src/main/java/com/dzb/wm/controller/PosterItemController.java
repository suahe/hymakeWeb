package com.dzb.wm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.console.entity.MediaFileEntity;
import com.dzb.console.syslog.SysLog;
import com.dzb.wm.entity.PosterItemEntity;
import com.dzb.wm.service.PosterItemService;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_poster_item 表Controller类
 * @author daizb@hymake.com
 * @date 2020-06-15 09:19:15
 * @version 1.0
 * @remark create by ca
 */
 
@Controller
@RequestMapping("pic")
public class PosterItemController{

	private static final Logger log = LoggerFactory.getLogger(PosterItemController.class);

	@Autowired
	private PosterItemService posterItemService;
	
	/**
	 * @Description 初始化列表页面
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
  	@SysLog(moduleName = "公告项目管理", submoduleName = "初始化列表页面", params = {})
	@RequestMapping(value = "/load",method = RequestMethod.GET)
	public String load(Model model) {
		
		log.debug("初始化列表页面");
		return "wm/t_poster_item_load";
	}
  	
  	@SysLog(moduleName = "公告项目管理", submoduleName = "初始化新增图片页面", params = {})
	@RequestMapping(value = "/loadImageAdd",method = RequestMethod.GET)
	public String loadImageAdd(Model model) {
		
		log.debug("初始化新增图片页面");
		return "wm/t_poster_item_image_add";
	}
  	
  	@SysLog(moduleName = "公告项目管理", submoduleName = "初始化编辑图片页面", params = {"posterItemId"})
	@RequestMapping(value = "/loadImageEdit",method = RequestMethod.GET)
	public String loadImageEdit(@RequestParam("posterItemId") long posterItemId, Model model) {
		
		log.debug("初始化编辑图片页面");
		PosterItemEntity posterItemEntity = posterItemService.getByPkey(posterItemId);
		model.addAttribute("posterItemEntity", posterItemEntity);
		return "wm/t_poster_item_image_edit";
	}
  	
  	@SysLog(moduleName = "公告项目管理", submoduleName = "初始化新增视频页面", params = {})
	@RequestMapping(value = "/loadVideoAdd",method = RequestMethod.GET)
	public String loadVideoAdd(Model model) {
		
		log.debug("初始化新增视频页面");
		return "wm/t_poster_item_video_add";
	}
  	
  	@SysLog(moduleName = "公告项目管理", submoduleName = "初始化编辑视频页面", params = {"posterItemId"})
	@RequestMapping(value = "/loadVideoEdit",method = RequestMethod.GET)
	public String loadVideoEdit(@RequestParam("posterItemId") long posterItemId, Model model) {
		
		log.debug("初始化编辑图片页面");
		PosterItemEntity posterItemEntity = posterItemService.getByPkey(posterItemId);
		model.addAttribute("posterItemEntity", posterItemEntity);
		return "wm/t_poster_item_video_edit";
	}
	
	/**
	 * @Description 查询列表
	 * @param page
	 * @param rows
	 * @param model
	 * @param posterItemEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "查询列表", params = {"offset","limit","posterItemEntity"})
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<PosterItemEntity> list(@RequestParam(value="offset",required=false,defaultValue="1") int offset,@RequestParam(value="limit",required=false,defaultValue="10") int limit,PosterItemEntity posterItemEntity, Model model) {
		
		return posterItemService.getPagination(offset, limit ,posterItemEntity);
	}
	
	/**
	 * @Description 保存新增信息
	 * @param posterItemEntity
	 * @param result
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "保存新增信息", params = {"posterItemId", "type", "name", "url", "isTarget"})
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public int save(@RequestParam("posterItemId") long posterItemId,
					@RequestParam("type") String type,
					@RequestParam("name") String name,
					@RequestParam("url") String url,
					@RequestParam("mobileUrl") String mobileUrl,
					@RequestParam("isTarget") String isTarget,
					@RequestParam("media") String media,
					@RequestParam("mobileMedia") String mobileMedia) {
		
		return posterItemService.save(posterItemId, type, name, url,mobileUrl, isTarget, media,mobileMedia);
	}
	
	/**
	 * @Description 通过主键获取信息
	 * @param posterId
	 * @param model
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "通过主键获取信息", params = {"posterItemId"})
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public PosterItemEntity view(@RequestParam("posterItemId") long posterItemId,Model model) {
		
		return posterItemService.getByPkey(posterItemId);
	}
	
	/**
	 * @Description 删除信息
	 * @param posterId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "删除信息", params = {"posterItemId"})
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	@ResponseBody
	public int del(@RequestParam("posterItemId") long posterItemId) {
		
		return posterItemService.delete(posterItemId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "排序下降", params = {"posterItemId"})
	@RequestMapping(value = "/down",method = RequestMethod.GET)
	@ResponseBody
	public int down(@RequestParam("posterItemId") long posterItemId) {
		
		return posterItemService.down(posterItemId);
	}
	
	/**
	 * @Description 排序下降
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	@SysLog(moduleName = "公告项目管理", submoduleName = "排序上升", params = {"posterItemId"})
	@RequestMapping(value = "/up",method = RequestMethod.GET)
	@ResponseBody
	public int up(@RequestParam("posterItemId") long posterItemId) {
		
		return posterItemService.up(posterItemId);
	}
	
	@SysLog(moduleName = "公告项目管理", submoduleName = "发布公告", params = {"posterItemId"})
	@RequestMapping(value = "/push",method = RequestMethod.POST)
	@ResponseBody
	public int push(@RequestParam("posterItemId") long posterItemId) {
		
		return posterItemService.updatePushTimeByPosterItemId(posterItemId, new Date());
	}
	
	@SysLog(moduleName = "公告项目管理", submoduleName = "下架公告", params = {"posterItemId"})
	@RequestMapping(value = "/pull",method = RequestMethod.POST)
	@ResponseBody
	public int pull(@RequestParam("posterItemId") long posterItemId) {
		
		return posterItemService.updatePushTimeByPosterItemId(posterItemId, null);
	}
	
	@SysLog(moduleName = "公告项目管理", submoduleName = "获取图片信息", params = {"posterItemId"})
	@GetMapping(value= "/loadImage")
    @ResponseBody
    public ResponseEntity<byte[]> loadImage(@RequestParam("posterItemId") long posterItemId,@RequestParam(value = "mark",required = false) String mark) {
        
    	MediaFileEntity mediaFileEntity = posterItemService.posterItemId(posterItemId,mark);
		if(null == mediaFileEntity) {
			return null;
		}else {
			final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaFileEntity.getMediaType());
	        return new ResponseEntity<>(mediaFileEntity.getMedia(), headers, HttpStatus.OK);
		}
    }
    
    @SysLog(moduleName = "公告项目管理", submoduleName = "获取有效视频的数量", params = {"type"})
	@RequestMapping(value = "/countByType",method = RequestMethod.GET)
	@ResponseBody
	public int countByType(@RequestParam("type") String type) {
		
		return posterItemService.countByType(type);
	}

}