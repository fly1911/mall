package com.mall.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mall.dao.ProductDAO;
import com.mall.pojo.Product;
import com.mall.util.C3P0Util;

public class ProductDAOImpl implements ProductDAO {
	
	QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public List<Product> findHotProducts() throws Exception {
		String sql = "select * from product where pflag =0 order by is_hot desc limit 9;";
		List<Product> hotProducts = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return hotProducts;
	}

	@Override
	public List<Product> findNewProducts() throws Exception {
		String sql = "select * from product where pflag =0 order by pdate desc limit 9;";
		List<Product> newProducts = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return newProducts;
	}

	@Override
	public Product get(String pid) throws Exception{
		String sql = "select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	@Override
	public long count(String cid) throws Exception {
		String sql = "select count(*) from product where cid=?";
		long count = (long) runner.query(sql, new ScalarHandler(),cid);
		return count;
	}

	@Override
	public List<Product> findListByCid(String cid, int start, int pageSize) throws Exception{
		String sql = "select * from product where cid=? order by pdate limit ?,?";
		List<Product> data = runner.query(sql, new BeanListHandler<Product>(Product.class), cid, start, pageSize);
		return data;
	}
}
