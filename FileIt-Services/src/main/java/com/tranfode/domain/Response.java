package com.tranfode.domain;

public class Response {
	
	private CustomHeader customHeader;
	private BusinessErrorData businessErrorData;

	public BusinessErrorData getBusinessErrorData() {
		return businessErrorData;
	}

	public void setBusinessErrorData(BusinessErrorData businessErrorData) {
		this.businessErrorData = businessErrorData;
	}

	public CustomHeader getCustomHeader() {
		return customHeader;
	}

	public void setCustomHeader(CustomHeader customHeader) {
		this.customHeader = customHeader;
	}

}