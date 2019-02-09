package com.tranfode.util;

import com.tranfode.Constants.BinderConstants;

public class FormatterUtil {

	public String doFormat(String prefix, String sufix) {
		String formattedString=prefix+"#"+BinderConstants.TRANFODE_KEY+"#"+sufix;
		return formattedString;
	}
	
	public String undoFormat(String input, String key) {
		String bookName=null;
		String defaultKey="G000";
		String[] arr=input.split("#");
		if(defaultKey.equals(key)) {
			bookName=arr[0];
		}else if(null!=key && arr.length>2) {
			if(key.equals(arr[2]) || defaultKey.equals(arr[2])) {
				bookName=arr[0];
			}
		}

		return bookName;
	}
	
	
	
	/*public String undoFormat(String input, String key) {
		String  regex="(.)(#"+BinderConstants.TRANFODE_KEY+"#)";
		if(null!=key) {
			regex=regex+"("+key+")\\z";
		}
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);

		if(matcher.find()) {
			//matcher.start();
			return input.substring(0, matcher.start()+1);

		} else {
			return null;
		} 
	}*/
	
}
