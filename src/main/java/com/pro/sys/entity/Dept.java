package com.pro.sys.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Dept {

	private Integer deptId;
	private String deptName;
	private String des;
	private int state;
	private Integer parentId;
	private Date createTime;
	private Date updateTime;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", des="
				+ des + ", state=" + state + ", parentId=" + parentId
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	public Dept(Integer deptId, String deptName, String des, int state,
			Integer parentId, Date createTime, Date updateTime) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.des = des;
		this.state = state;
		this.parentId = parentId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
