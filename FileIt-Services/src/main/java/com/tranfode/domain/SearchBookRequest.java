package com.tranfode.domain;

public class SearchBookRequest  extends AbstractRequest {

	String bookName;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}