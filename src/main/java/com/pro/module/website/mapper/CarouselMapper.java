package com.pro.module.website.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.module.website.entity.Carousel;

/**
 * 轮播图
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午3:21:15
 */
public interface CarouselMapper {
	


	/***
	 * 根据条件分页获取轮播图列表
	 * @param start
	 * @param rows
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	List<Carousel> findCarouselListByState(@Param("start")int page, @Param("rows")int rows,
			@Param("title")String title,
			@Param("startTime")Date startTime,@Param("endTime")Date endTime,
			@Param("state")Integer state);

	/***
	 * 获取轮播图总数
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	int findCarouselCount(@Param("title")String title,
			@Param("startTime")Date startTime,@Param("endTime")Date endTime,
			@Param("state")Integer state);

	/***
	 * 保存轮播图
	 * @param carousel
	 */
	void saveCarousel(Carousel carousel);
	/***
	 * 更新轮播图
	 * @param carousel
	 */
	void updateCarousel(Carousel carousel);

	/***
	 * 获取所有有效的轮播图
	 * @return
	 */
	List<Carousel> findAllEnableCarousel();
}
