package com.pro.module.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.module.website.entity.Article;
import com.pro.module.website.entity.Product;
import com.pro.module.website.entity.ProductType;
import com.pro.module.website.mapper.ProductMapper;
import com.pro.module.website.service.ProductService;
import com.pro.sys.util.Pager;

/**
 * 产品展示服务累
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月18日 下午2:22:06
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> findProductListByState(int page, int rows,
			Integer state, Date startTime, Date endTime, String title,
			Integer type) {
		return this.productMapper.findProductListByState((page-1)*rows, rows, title,  startTime, endTime, state,type);
	}

	@Override
	public int findProductCount(String title, Date startTime, Date endTime,
			Integer state, Integer type) {
		return this.productMapper.findPrductCount(title, startTime, endTime, state,type);
	}

	@Override
	public List<ProductType> findAllProductType() {
		return this.productMapper.findAllProductType();
	}

	@Override
	public void saveProduct(Product product) {
		this.productMapper.saveProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.productMapper.updateProduct(product);
	}

	@Override
	public Pager<Product> findProductListByPage(Integer type,Pager<Product> pager) {
		List<Product> list =  this.productMapper.findProductListByState( (pager.getPageNumber()-1) * pager.getLimit(), pager.getLimit(), null, null, null, 0, type);
		pager.setList(list);
		int count = this.productMapper.findPrductCount(null, null, null, 0, type);
		pager.init(count, pager.getPageNumber(), pager.getLimit());
		return pager;
	}

	@Override
	public Product findProductByIdAndType(Integer type, Integer id) {
		return this.productMapper.findProductByIdAndType(type, id);
	}
	
	
	
}
