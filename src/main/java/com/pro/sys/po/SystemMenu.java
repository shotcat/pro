package com.pro.sys.po;

import java.util.Date;
import java.util.List;
import java.util.Set;

/***
 * 系统菜单
 * @author gaoyuandong
 * 2015年4月21日 上午9:49:10
 */
public class SystemMenu {
	private Integer resourceId;

    private String resourceName;

    private Integer type;

    private Integer parentId;

    private String icon;

    private Integer sort;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String url;

    private String target;
    
    private Set<SystemMenu> childrenMenus;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Set<SystemMenu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(Set<SystemMenu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}
    
}
