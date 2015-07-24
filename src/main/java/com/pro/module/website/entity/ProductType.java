package com.pro.module.website.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 展示产品类型
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月18日 下午3:15:17
 */
public class ProductType {
	
	private Integer id;
	private String typeName;
	private int state;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime
	 */
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
