package com.tranfode.domain;

import java.util.List;

public class BinderList {
	String id ;
	String name;
	String classification;
	List<Children> children;
	String  groupId;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public List<Children> getChildren() {
		return children;
	}
	public void setChildrenList(List<Children> children) {
		this.children = children;
	}
	

	

}
