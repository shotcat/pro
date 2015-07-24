package com.pro.module.website.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 展示产品实体
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月18日 下午2:14:01
 */
public class Product {

	private Integer id;
	private String title;
	private String remark;
	private Integer type;
	private String content;
	private int state;
	private int sort;
	private String image;
	private Date createTime;
	private Date updateTime;
	private String typeName;
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
