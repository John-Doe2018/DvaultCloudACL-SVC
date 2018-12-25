package com.tranfode.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActiveUser {

	public String userName;
	public String key;
	public String status;
	public Date loginTime;
	
	public ActiveUser() {
		super();
	}
	
	public ActiveUser(String userName, String key, String status, Date loginTime) {
		super();
		this.userName = userName;
		this.key = key;
		this.status = status;
		this.loginTime = loginTime;
	}
	
	@Override
	public String toString() {
		return "ActiveUser [userName=" + userName + ", key=" + key + ", status=" + status + ", loginTime=" + loginTime
				+ "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	
}
