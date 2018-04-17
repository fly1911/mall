package com.mall.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyDateConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class clazz, Object value) {
		String dateStr = (String) value;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
