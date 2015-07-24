package com.pro.module.website.service;

import java.util.Date;
import java.util.List;

import com.pro.module.website.entity.Product;
import com.pro.module.website.entity.ProductType;
import com.pro.sys.util.Pager;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月18日 下午3:47:45
 */
public interface ProductService {

	/***
	 * 分页获取产品展示列表
	 * @param page
	 * @param rows
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @param title
	 * @param type
	 * @return
	 */
	List<Product> findProductListByState(int page, int rows, Integer state,
			Date startTime, Date endTime, String title, Integer type);

	/***
	 * 
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @param type
	 * @return
	 */
	int findProductCount(String title, Date startTime, Date endTime,
			Integer state, Integer type);

	/**
	 * 
	 * @return
	 */
	List<ProductType> findAllProductType();

	/***
	 * 保存产品
	 * @param product
	 */
	void saveProduct(Product product);

	/***
	 * 更新产品
	 * @param product
	 */
	void updateProduct(Product product);

	Pager<Product> findProductListByPage(Integer type,Pager<Product> page);

	/***
	 * 根据主键和id查询产品详情
	 * @param type
	 * @param id
	 * @return
	 */
	Product findProductByIdAndType(Integer type, Integer id);

}
