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
import com.pro.module.website.entity.Product;
import com.pro.module.website.entity.ProductType;
import com.pro.module.website.service.ProductService;
import com.pro.sys.annotation.DateParser;
import com.pro.sys.util.JsonResult;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("admin/website/productList");
		return modelAndView;
	}
	
	@RequestMapping("/findProductList")
	@ResponseBody
	public Map<String, Object>  findArticlelList(@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="10")int rows,
			Integer state,
			@DateParser(pattern="yyyy-MM-dd") Date startTime,
			@DateParser(pattern="yyyy-MM-dd") Date endTime,
			String title,
			Integer type) {
		List<Product> products = this.productService.findProductListByState(page, rows, state, startTime, endTime, title,type);
		int count = this.productService.findProductCount(title,startTime,endTime,state,type);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", products);
		dataMap.put("total", count);
		return dataMap;
		
	}
	/***
	 * 
	 * @return
	 */
	@RequestMapping("/findAllProductType")
	@ResponseBody
	public List<ProductType> findAllProductType() {
		
		return this.productService.findAllProductType();
	}
	
	
	/***
	 * 保存轮播图
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/updateProduct")
	@ResponseBody
	public JsonResult updateProduct(Product product) {
		JsonResult jsonResult = new JsonResult();
		
		if(product.getId() == null) {
			jsonResult.setMessage("当前产品不存在");
			return jsonResult;
		}
		if(StringUtils.isBlank(product.getTitle())) {
			jsonResult.setMessage("当前产品标题不能为空");
			return jsonResult;
		}
		if(StringUtils.isBlank(product.getRemark())) {
			jsonResult.setMessage("当前产品概述不能为空");
			return jsonResult;
		}
		if(StringUtils.isBlank(product.getContent())) {
			jsonResult.setMessage("当前产品内容不能为空");
			return jsonResult;
		}
		
		
		try {
			this.productService.updateProduct(product);
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
	@RequestMapping("/saveProduct")
	@ResponseBody
	public JsonResult saveProduct(Product product) {
		JsonResult jsonResult = new JsonResult();
		try {
			if(StringUtils.isBlank(product.getTitle())) {
				jsonResult.setMessage("当前产品描述标题不能为空");
				return jsonResult;
			}
			if(StringUtils.isBlank(product.getRemark())) {
				jsonResult.setMessage("当前产品概述内容不能为空");
				return jsonResult;
			}
			if(StringUtils.isBlank(product.getContent())) {
				jsonResult.setMessage("当前产品描述内容不能为空");
				return jsonResult;
			}
			this.productService.saveProduct(product);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功!");
		} catch (Exception e) {
			jsonResult.setMessage("更新失败，请重试");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
}
