package com.mall.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.mall.pojo.Product;
import com.mall.service.ProductService;
import com.mall.service.impl.ProductServiceImpl;

public class TestBeanUtils {
	
	@Test
	public void setField() throws Exception{
		ProductService productService = new ProductServiceImpl();
		Product product = productService.get("7");
		System.out.println("before:"+product);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pname", "炸弹7");
		map.put("market_price", null);
		
		BeanUtils.populate(product, map);
		System.out.println("after:"+product);
		//说明map中有和product中属性名相同的key就会赋值给product,没有就不做操作
	}

}
