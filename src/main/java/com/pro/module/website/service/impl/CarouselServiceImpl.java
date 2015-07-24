package com.pro.module.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.module.website.entity.Carousel;
import com.pro.module.website.mapper.CarouselMapper;
import com.pro.module.website.service.CarouselService;
import com.pro.sys.exception.ServiceException;

/**
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午4:31:27
 */
@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {

	@Autowired
	private CarouselMapper carouselMapper;
	
	@Override
	public List<Carousel> findCarouselListByState(int page, int rows,
			Integer state, Date startTime, Date endTime, String title) {
		return this.carouselMapper.findCarouselListByState((page-1)*rows, rows,title,startTime,endTime,state);
	}

	@Override
	public int findCarouselCount(String title, Date startTime, Date endTime,
			Integer state) {
		return this.carouselMapper.findCarouselCount(title, startTime, endTime, state);
	}

	@Override
	public void saveCarousel(Carousel carousel) throws ServiceException {
		
		this.carouselMapper.saveCarousel(carousel);
	}

	@Override
	public void updateCarousel(Carousel carousel) {
		
		this.carouselMapper.updateCarousel(carousel);
	}

	@Override
	public List<Carousel> findAllEnableCarousel() {
		return this.carouselMapper.findAllEnableCarousel();
	}
	
	
}
