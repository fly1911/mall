package com.mall.util;

import java.util.UUID;

public class UUIDUtils {
	
	public static String ranUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
