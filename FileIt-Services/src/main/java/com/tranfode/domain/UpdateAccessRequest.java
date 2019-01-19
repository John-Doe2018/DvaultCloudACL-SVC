package com.tranfode.domain;

import java.util.ArrayList;

public class UpdateAccessRequest  extends AbstractRequest{
	private String role;
	private ArrayList<String> accesslist=new ArrayList<String>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getAccesslist() {
		return accesslist;
	}

	public void setAccesslist(ArrayList<String> accesslist) {
		this.accesslist = accesslist;
	}
	

}
