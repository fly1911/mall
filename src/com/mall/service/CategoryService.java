package com.mall.service;

import java.util.List;

import com.mall.pojo.Category;

public interface CategoryService {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:查询所有分类（ajax）
	 */
	List<Category> list() throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:获取单个商品
	 */
	public Category get(String id) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:新增分类
	 */
	public void add(Category category) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:删除分类
	 */
	public void del(String id) throws Exception; 
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:编辑分类
	 */
	public void update(Category category) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:直接查询数据库
	 */
	public List<Category> findAllCategory() throws Exception;
}
