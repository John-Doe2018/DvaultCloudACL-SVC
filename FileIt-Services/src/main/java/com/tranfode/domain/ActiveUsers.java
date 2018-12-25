package com.tranfode.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ActiveUsers")
@XmlAccessorType (XmlAccessType.FIELD)
public class ActiveUsers {

	@XmlElement(name = "User")
	public List<ActiveUser> activeUserList;

	public List<ActiveUser> getActiveUserList() {
		return activeUserList;
	}

	public void setActiveUserList(List<ActiveUser> activeUserList) {
		this.activeUserList = activeUserList;
	}	
}
