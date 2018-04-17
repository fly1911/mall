package com.mall.service;

import java.util.List;

import com.mall.pojo.Category;

public interface CategoryService {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:查询所有分类
	 */
	List<Category> list() throws Exception;
}
