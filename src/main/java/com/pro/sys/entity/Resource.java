package com.pro.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Resource implements Serializable {
    private Integer resourceId;

    private String resourceName;

    private int type;

    private Integer parentId;

    private String icon;

    private int sort;

    private int state;

    private Date createTime;

    private Date updateTime;

    private String url;

    private String target;

    private static final long serialVersionUID = 1L;

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



    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	@JsonProperty(value="status")
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

   
}