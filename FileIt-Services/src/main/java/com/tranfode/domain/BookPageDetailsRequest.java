package com.tranfode.domain;

public class BookPageDetailsRequest extends AbstractRequest {
	String bookName;
	String fileName;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
