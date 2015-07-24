package com.pro.module.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.module.website.entity.Article;
import com.pro.module.website.mapper.ArticleMapper;
import com.pro.module.website.service.ArticleService;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.util.Pager;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午4:31:27
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public List<Article> findArticleListByState(int page, int rows,
			Integer state, Date startTime, Date endTime, String title,Integer type) {
		return this.articleMapper.findArticleListByState((page-1)*rows, rows,title,startTime,endTime,state,type);
	}

	@Override
	public int findArticleCount(String title, Date startTime, Date endTime,
			Integer state,Integer type) {
		return this.articleMapper.findArticleCount(title, startTime, endTime, state,type);
	}

	@Override
	public void saveArticle(Article article) throws ServiceException {
		
		this.articleMapper.saveArticle(article);
	}

	@Override
	public void updateArticle(Article article) {
		
		this.articleMapper.updateArticle(article);
	}

	@Override
	public List<Article> findArticleType(int articleType1, int start, int end) {
		return this.articleMapper.findArticleType(articleType1, start, end);
	}

	@Override
	public Article findArticleById(Integer id) {
		// TODO Auto-generated method stub
		return this.articleMapper.findArticleById(id);
	}

	@Override
	public Pager<Article> findArticleList(Integer type, Pager<Article> pager) {
		List<Article> list =  this.articleMapper.findArticleList(type, (pager.getPageNumber()-1) * pager.getLimit(), pager.getLimit());
		pager.setList(list);
		int count =  this.articleMapper.findArticleCountByType(type);
		pager.init(count, pager.getPageNumber(), pager.getLimit());
		return pager;
		
	}
	
	/***
	 * 获取关于我们
	 */
	public Article findAboutUs() {
		// TODO Auto-generated method stub
		return this.articleMapper.findAboutUs();
	}

	
	
	
}
