package com.mall.util;

import javax.servlet.http.Cookie;

public class CookieUtils {
	
	public static Cookie getCookieByName(Cookie[] cookieArr, String name) {
		if ("".equals(name) || name == null) {
			return null;
		}
		
		if (cookieArr != null) {
			for (int i = 0;i < cookieArr.length; i++) {
				Cookie cookie = cookieArr[i];
				if(name.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}

}
