package com.mall.test;

import java.util.List;

import org.junit.Test;

import com.mall.pojo.Category;
import com.mall.service.impl.CategoryServiceImpl;

public class CategoryServiceImplTest {

	@Test
	public void testList() throws Exception {
		CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
		List list = categoryServiceImpl.list();
		for (Object object : list) {
			System.out.println(object);
		}
		
	}

}
