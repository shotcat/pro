package com.pro.module.website.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pro.module.website.entity.Article;
import com.pro.module.website.service.ArticleService;
import com.pro.sys.util.Pager;

@Controller
public class NewsController {

	@Autowired
	private ArticleService articleService;
	@RequestMapping("/news/info_{id}")
	public ModelAndView newsInfo(@PathVariable("id")Integer id) {
		
		ModelAndView andView = new ModelAndView("front/newsInfo");
		Article article = this.articleService.findArticleById(id);
		
		andView.addObject("news", article);
		return andView;
	}
	@RequestMapping("/news/list_{type}_{pageNumber}")
	public ModelAndView newsList(@PathVariable("type")Integer type,@PathVariable(value="pageNumber")Integer pageNumber,Pager<Article> page) {
		
		ModelAndView andView = new ModelAndView("front/newsList");
		page.setPageNumber(pageNumber);
		Pager<Article> pager = this.articleService.findArticleList(type,page);
		andView.addObject("type", type);
		andView.addObject("pager", pager);
		return andView;
	}
	@RequestMapping("/dynamic/info_{id}")
	public ModelAndView dynamicInfo(@PathVariable("id")Integer id) {
		
		ModelAndView andView = new ModelAndView("front/dynamicInfo");
		Article article = this.articleService.findArticleById(id);
		
		andView.addObject("dynamic", article);
		return andView;
	}
	@RequestMapping("/dynamic/list_{type}_{pageNumber}")
	public ModelAndView dynamicList(@PathVariable("type")Integer type,@PathVariable(value="pageNumber")Integer pageNumber,Pager<Article> page) {
		
		ModelAndView andView = new ModelAndView("front/dynamicList");
		page.setPageNumber(pageNumber);
		Pager<Article> pager = this.articleService.findArticleList(type,page);
		andView.addObject("type", type);
		andView.addObject("pager", pager);
		return andView;
	}
	
	@RequestMapping("/about/aboutUs")
	public ModelAndView about() {
		
		ModelAndView andView = new ModelAndView("front/aboutUs");
		List<Article> list = this.articleService.findArticleType(3, 0, 1);
		if(!CollectionUtils.isEmpty(list)) {
			andView.addObject("aboutUs", list.get(0));
		}
		
		return andView;
	}
	@RequestMapping("/job/joinUs")
	public ModelAndView joinUs() {
		
		ModelAndView andView = new ModelAndView("front/joinUs");
		List<Article> list = this.articleService.findArticleType(4, 0, 1);
		if(!CollectionUtils.isEmpty(list)) {
			andView.addObject("joinUs", list.get(0));
		}
		return andView;
	}
	@RequestMapping("/contact/contactUs")
	public ModelAndView contactUs() {
		
		ModelAndView andView = new ModelAndView("front/contactUs");
		List<Article> list = this.articleService.findArticleType(5, 0, 1);
		if(!CollectionUtils.isEmpty(list)) {
			andView.addObject("contactUs", list.get(0));
		}
		return andView;
	}
}
