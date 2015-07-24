package com.pro.module.website.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.module.website.entity.Product;
import com.pro.module.website.entity.ProductType;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月18日 下午2:38:07
 */
public interface ProductMapper {

	/**
	 * @param start
	 * @param rows
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @param type
	 * @return
	 */
	List<Product> findProductListByState(@Param("start")int start, @Param("rows")int rows, @Param("title")String title,
			@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("state")Integer state,@Param("type")Integer type);

	int findPrductCount(@Param("title")String title,@Param("startTime") Date startTime, @Param("endTime")Date endTime,
			@Param("state")Integer state, @Param("type")Integer type);


	List<ProductType> findAllProductType();

	void saveProduct(Product product);

	void updateProduct(Product product);

	/***
	 * 根据id 和类型查询产品详情信息
	 * @param type
	 * @param id
	 * @return
	 */
	Product findProductByIdAndType(@Param("type")Integer type, @Param("id")Integer id);
	
	
}
