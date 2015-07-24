package com.pro.module.website.web.action;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pro.module.website.entity.Article;
import com.pro.module.website.entity.Carousel;
import com.pro.module.website.entity.Product;
import com.pro.module.website.service.ArticleService;
import com.pro.module.website.service.CarouselService;
import com.pro.module.website.service.ProductService;

@Controller
public class FrontController {

	@Autowired
	private CarouselService carouselService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ProductService productService;
	
	private static final int ARTICLE_TYPE_1 = 1;
	private static final int ARTICLE_TYPE_2 = 2;
	private static final int ARTICLE_TYPE_3 = 3;
	private static final int ARTICLE_TYPE_4 = 4;
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView andView = new ModelAndView("front/index");
		//TODO 
		List<Carousel> carousels = this.carouselService.findAllEnableCarousel();
		//TODO
		List<Article> articles = this.articleService.findArticleType(ARTICLE_TYPE_1,0,10);
		List<Article> news = this.articleService.findArticleType(ARTICLE_TYPE_2,0,10);
		List<Product> products = this.productService.findProductListByState(1, 10, 0, null, null, null, null);
		
		andView.addObject("carousels", carousels);
		andView.addObject("articles", articles);
		andView.addObject("news", news);
		andView.addObject("products", products);
		return andView;
	}
}
