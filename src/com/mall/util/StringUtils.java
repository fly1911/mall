package com.mall.util;

public class StringUtils {
	
	public static boolean isEmpty(String str){
		if ("".equals(str) || str == null) {
			return true;
		}
		return false;
	}
}
