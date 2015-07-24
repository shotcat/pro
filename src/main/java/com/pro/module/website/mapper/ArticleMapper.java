package com.pro.module.website.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.module.website.entity.Article;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月15日 下午5:15:24
 */
public interface ArticleMapper {

	void updateArticle(Article article);

	void saveArticle(Article article);

	int findArticleCount(@Param("title")String title,@Param("startTime") Date startTime, @Param("endTime")Date endTime,
			@Param("state")Integer state, @Param("type")Integer type);

	List<Article> findArticleListByState(@Param("start")int start, @Param("rows")int rows, @Param("title")String title,
			@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("state")Integer state,@Param("type")Integer type);

	List<Article> findArticleType(@Param("type")int type, @Param("start")int start,@Param("end") int end);

	Article findArticleById(@Param("id")Integer id);

	List<Article> findArticleList(@Param("type")Integer type,@Param("start") int start,@Param("size") Integer size);

	int findArticleCountByType(@Param("type")Integer type);

	/***
	 * 获取关于我们
	 * @return
	 */
	Article findAboutUs();

}
