package com.pro.module.website.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/***
 * 轮播图实体类
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月14日 下午3:13:46
 */
public class Carousel {

	private Integer id;
	private String title;
	private int state;
	private String des;
	private int sort;
	private String url;
	private Date createTime;
	private Date updateTime;
	private String ImageUrl;
	
	
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
