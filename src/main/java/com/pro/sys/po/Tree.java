package com.pro.sys.po;

import java.util.List;
/***
 * easyui tree
 * @author gaoyuandong
 *
 */
public class Tree {

	private Integer id;
	private String text;
	private List<Tree> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
}
