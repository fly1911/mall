package com.mall.util;

public class FileUploadUtils {
	
	public static String getUniqueName(String fileName) {
		if (!StringUtils.isEmpty(fileName)) {
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			String uniqueName = UUIDUtils.ranUUID() + suffix;
			return uniqueName;
		}
		return null;
	}

}
