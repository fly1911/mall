package com.mall.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mall.dao.CategoryDAO;
import com.mall.pojo.Category;
import com.mall.util.C3P0Util;

public class CategoryDAOImpl implements CategoryDAO {

	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public List<Category> list() throws Exception{
		String sql = "select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

}
