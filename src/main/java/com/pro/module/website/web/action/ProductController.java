package com.pro.module.website.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pro.module.website.entity.Article;
import com.pro.module.website.entity.Product;
import com.pro.module.website.entity.ProductType;
import com.pro.module.website.service.ProductService;
import com.pro.sys.util.Pager;

/**
 * 产品展示相关
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月20日 上午11:33:45
 */
@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list_{type}_{pageNumber}")
	public ModelAndView productList(@PathVariable("type")Integer type,@PathVariable(value="pageNumber")Integer pageNumber,Pager<Product> page) {
		
		ModelAndView andView = new ModelAndView("front/productList");
		page.setPageNumber(pageNumber);
		Pager<Product> pager = this.productService.findProductListByPage(type,page);
		List<ProductType> types = this.productService.findAllProductType();
		andView.addObject("type", type);
		andView.addObject("types", types);
		andView.addObject("pager", pager);
		return andView;
	}
	@RequestMapping("/info_{type}_{id}")
	public ModelAndView productInfo(@PathVariable("type")Integer type,@PathVariable("id")Integer id) {
		
		ModelAndView andView = new ModelAndView("front/productInfo");
		Product product = this.productService.findProductByIdAndType(type,id);
		andView.addObject("type", type);
		andView.addObject("product", product);
		return andView;
	}

}
