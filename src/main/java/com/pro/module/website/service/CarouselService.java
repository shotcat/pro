package com.pro.module.website.service;

import java.util.Date;
import java.util.List;

import com.pro.module.website.entity.Carousel;
import com.pro.sys.exception.ServiceException;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午4:31:00
 */
public interface CarouselService {

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
	List<Carousel> findCarouselListByState(int page, int rows, Integer state,
			Date startTime, Date endTime, String title);

	/**
	 * 
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	int findCarouselCount(String title, Date startTime, Date endTime,
			Integer state);

	/***
	 * 保存轮播图
	 * @param carousel
	 * @throws ServiceException
	 */
	void saveCarousel(Carousel carousel) throws ServiceException;

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
