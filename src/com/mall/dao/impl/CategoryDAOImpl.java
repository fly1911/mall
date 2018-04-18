package com.mall.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

	@Override
	public Category get(String id) throws Exception {
		String sql = "select * from category where cid=?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class), id);
		return category;
	}

	@Override
	public void add(Category category) throws Exception {
		String sql = "insert into category(cid, cname) values(?, ?)";
		runner.update(sql, category.getCid(), category.getCname());
	}

	@Override
	public void del(String id) throws Exception {
		String sql = "delete from category where cid=?";
		runner.update(sql, id);
	}

	@Override
	public void update(Category category) throws Exception {
		String sql = "update category set cname = ? where cid=?";
		runner.update(sql, category.getCname(), category.getCid());
	}
}
