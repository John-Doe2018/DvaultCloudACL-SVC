package com.tranfode.domain;

public class AddClassificationRequest extends AbstractRequest{
	String classificationName;

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
}
