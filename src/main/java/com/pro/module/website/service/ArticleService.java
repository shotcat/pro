package com.pro.module.website.service;

import java.util.Date;
import java.util.List;

import com.pro.module.website.entity.Article;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.util.Pager;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午4:31:00
 */
public interface ArticleService {

	/***
	 * 
	 * @param page
	 * @param rows
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @param title
	 * @return
	 */
	List<Article> findArticleListByState(int page, int rows, Integer state,
			Date startTime, Date endTime, String title,Integer type);

	/**
	 * 
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	int findArticleCount(String title, Date startTime, Date endTime,
			Integer state,Integer type);

	/***
	 * 保存文章
	 * @param carousel
	 * @throws ServiceException
	 */
	void saveArticle(Article article) throws ServiceException;

	/***
	 * 更新文章
	 * @param carousel
	 */
	void updateArticle(Article article);

	List<Article> findArticleType(int articleType1, int start, int end);

	Article findArticleById(Integer id);

	/***
	 * 获取文章列表
	 * @param type
	 * @param start
	 * @param size
	 * @return
	 */
	Pager<Article> findArticleList(Integer type,Pager<Article> pager);

	/***
	 * 获取关于我们
	 * @return
	 */
	Article findAboutUs();

	

	

}
