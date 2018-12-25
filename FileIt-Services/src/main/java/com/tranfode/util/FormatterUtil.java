package com.tranfode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tranfode.Constants.BinderConstants;

public class FormatterUtil {

	public String doFormat(String prefix, String sufix) {
		String formattedString=prefix+"#"+BinderConstants.TRANFODE_KEY+"#"+sufix;
		return formattedString;
	}
	
	public String undoFormat(String input, String key) {
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
	}
}
