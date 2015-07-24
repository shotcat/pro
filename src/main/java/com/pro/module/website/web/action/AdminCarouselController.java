package com.pro.module.website.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.module.website.entity.Carousel;
import com.pro.module.website.service.CarouselService;
import com.pro.sys.annotation.DateParser;
import com.pro.sys.entity.User;
import com.pro.sys.util.JsonResult;

/***
 * 轮播图管理
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午4:23:23
 */
@Controller
@RequestMapping("/admin/carousel")
public class AdminCarouselController {

	
	@Autowired
	private CarouselService carouselService;
	/***
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("admin/website/carouselList");
		return modelAndView;
	}
	
	@RequestMapping("/findCarouselList")
	@ResponseBody
	public Map<String, Object>  findCarouselList(@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="10")int rows,
			Integer state,
			@DateParser(pattern="yyyy-MM-dd") Date startTime,
			@DateParser(pattern="yyyy-MM-dd") Date endTime,
			String title) {
		List<Carousel> carousels = this.carouselService.findCarouselListByState(page, rows, state, startTime, endTime, title);
		int count = this.carouselService.findCarouselCount(title,startTime,endTime,state);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", carousels);
		dataMap.put("total", count);
		return dataMap;
		
	}
	
	/***
	 * 保存轮播图
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/saveCarousel")
	@ResponseBody
	public JsonResult saveCarousel(Carousel carousel) {
		JsonResult jsonResult = new JsonResult();
		try {
			if(carousel.getTitle() == null) {
				jsonResult.setMessage("当前轮播图标题不能为空");
				return jsonResult;
			}
			if(carousel.getDes() == null) {
				jsonResult.setMessage("当前轮播图描述不能为空");
				return jsonResult;
			}
			if(carousel.getUrl() == null) {
				jsonResult.setMessage("当前轮播图链接不能为空");
				return jsonResult;
			}
			if(carousel.getImageUrl() == null) {
				jsonResult.setMessage("当前轮播图片不能为空");
				return jsonResult;
			}
			this.carouselService.saveCarousel(carousel);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功!");
		} catch (Exception e) {
			jsonResult.setMessage("更新失败，请重试");
			e.printStackTrace();
		}
		return jsonResult;
	}
	/***
	 * 更新轮播图
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/updateCarousel")
	@ResponseBody
	public JsonResult updateCarousel(Carousel carousel) {
		JsonResult jsonResult = new JsonResult();
		
		if(carousel.getId() == null) {
			jsonResult.setMessage("当前轮播图不存在");
			return jsonResult;
		}
		if(carousel.getTitle() == null) {
			jsonResult.setMessage("当前轮播图标题不能为空");
			return jsonResult;
		}
		if(carousel.getDes() == null) {
			jsonResult.setMessage("当前轮播图描述不能为空");
			return jsonResult;
		}
		if(carousel.getUrl() == null) {
			jsonResult.setMessage("当前轮播图链接不能为空");
			return jsonResult;
		}
		if(carousel.getImageUrl() == null) {
			jsonResult.setMessage("当前轮播图片不能为空");
			return jsonResult;
		}
		try {
			this.carouselService.updateCarousel(carousel);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("更新成功!");
		} catch (Exception e) {
			jsonResult.setMessage("更新失败，请重试");
			e.printStackTrace();
			return jsonResult;
		}
		return jsonResult;
	}
}
