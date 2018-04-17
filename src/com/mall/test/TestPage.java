package com.mall.test;

import org.junit.Test;

import com.mall.pojo.PageBean;
import com.mall.pojo.Product;
import com.mall.service.ProductService;
import com.mall.service.impl.ProductServiceImpl;

public class TestPage {

	@Test
	public void testPage() throws Exception{
		ProductService productService = new ProductServiceImpl();
		PageBean<Product> page = productService.pageProduct("2", 1, 12);
		System.out.println(page);
	}
}
