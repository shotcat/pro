package com.pro.module.website.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.module.website.entity.Article;
import com.pro.module.website.entity.Carousel;
import com.pro.module.website.service.ArticleService;
import com.pro.sys.annotation.DateParser;
import com.pro.sys.util.JsonResult;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

	@Autowired
	private ArticleService articleService;
	/***
	 * 跳转到文章管理列表
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("admin/website/articleList");
		return modelAndView;
	}
	
	@RequestMapping("/findArticlelList")
	@ResponseBody
	public Map<String, Object>  findArticlelList(@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="10")int rows,
			Integer state,
			@DateParser(pattern="yyyy-MM-dd") Date startTime,
			@DateParser(pattern="yyyy-MM-dd") Date endTime,
			String title,
			Integer type) {
		List<Article> articles = this.articleService.findArticleListByState(page, rows, state, startTime, endTime, title,type);
		int count = this.articleService.findArticleCount(title,startTime,endTime,state,type);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", articles);
		dataMap.put("total", count);
		return dataMap;
		
	}
	
	/***
	 * 保存轮播图
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/updateArticle")
	@ResponseBody
	public JsonResult updateArticle(Article article) {
		JsonResult jsonResult = new JsonResult();
		
		if(article.getId() == null) {
			jsonResult.setMessage("当前文章不存在");
			return jsonResult;
		}
		if(StringUtils.isBlank(article.getTitle())) {
			jsonResult.setMessage("当前文章标题不能为空");
			return jsonResult;
		}
		if(StringUtils.isBlank(article.getContent())) {
			jsonResult.setMessage("当前文章内容不能为空");
			return jsonResult;
		}
		
		
		try {
			this.articleService.updateArticle(article);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("更新成功!");
		} catch (Exception e) {
			jsonResult.setMessage("更新失败，请重试");
			e.printStackTrace();
			return jsonResult;
		}
		return jsonResult;
	}
	
	
	/***
	 * 保存文章
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/saveArticle")
	@ResponseBody
	public JsonResult saveArticle(Article article) {
		JsonResult jsonResult = new JsonResult();
		try {
			if(StringUtils.isBlank(article.getTitle())) {
				jsonResult.setMessage("当前文章标题不能为空");
				return jsonResult;
			}
			if(StringUtils.isBlank(article.getContent())) {
				jsonResult.setMessage("当前文章内容不能为空");
				return jsonResult;
			}
			this.articleService.saveArticle(article);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功!");
		} catch (Exception e) {
			jsonResult.setMessage("更新失败，请重试");
			e.printStackTrace();
		}
		return jsonResult;
	}
}
