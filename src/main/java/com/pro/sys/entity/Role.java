package com.pro.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private int state;

    private String des;
    private Date createTime;
    private Date updateTime;
    
    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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